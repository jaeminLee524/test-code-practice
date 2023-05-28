package sample.cafekiosk.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.domain.product.ProductSellingType.SELLING;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductType;

class OrderTest {

    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        // given
        List<Product> products = List.of(
            createProduct("001", 1000),
            createProduct("002", 3000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        assertThat(order.getTotalPrice()).isEqualTo(4000);
    }

    @DisplayName("주문 생성 시 주문 상태는 INIT이다.")
    @Test
    void orderStatusIsInit() {
        // given
        List<Product> products = List.of(
            createProduct("001", 1000),
            createProduct("002", 3000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        assertThat(order.getOrderStatus()).isEqualByComparingTo(OrderStatus.INIT);
    }

    @DisplayName("주문 생성 시 주문 시간을 기록한다.")
    @Test
    void registeredDateTime() {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        List<Product> products = List.of(
            createProduct("001", 1000),
            createProduct("002", 3000)
        );

        // when
        Order order = Order.create(products, registeredDateTime);

        // then
        assertThat(order.getRegisteredDateTime()).isEqualTo(registeredDateTime);
    }

    private static Product createProduct(String productNumber, int price) {
        return Product.builder()
            .productNumber(productNumber)
            .type(ProductType.HANDMADE)
            .sellingType(SELLING)
            .name("메뉴 이름")
            .price(price)
            .build();
    }
}