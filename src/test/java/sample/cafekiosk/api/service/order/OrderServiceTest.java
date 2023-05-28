package sample.cafekiosk.api.service.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.domain.product.ProductSellingType.SELLING;
import static sample.cafekiosk.domain.product.ProductType.HANDMADE;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.api.service.order.response.OrderResponse;
import sample.cafekiosk.domain.ProductRepository;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductType;

@ActiveProfiles("test")
@SpringBootTest
//@DataJpaTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder() {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();

        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 3000);
        Product product3 = createProduct(HANDMADE, "003", 5000);
        productRepository.saveAll(List.of(product1, product2, product3));

        OrderCreateRequest request = OrderCreateRequest.builder()
            .productNumbers(List.of("001", "002"))
            .build();

        // when
        OrderResponse response = orderService.createOrder(request, registeredDateTime);

        // then
        assertThat(response.getId()).isNotNull();
        assertThat(response)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 4000);
        assertThat(response.getProducts()).hasSize(2);
        assertThat(response.getProducts())
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("002", 3000)
            );
    }

    private static Product createProduct(ProductType productType, String productNumber, int price) {
        return Product.builder()
            .productNumber(productNumber)
            .type(productType)
            .sellingType(SELLING)
            .name("메뉴 이름")
            .price(price)
            .build();
    }
}