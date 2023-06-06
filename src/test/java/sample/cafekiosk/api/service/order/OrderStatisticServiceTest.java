package sample.cafekiosk.api.service.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static sample.cafekiosk.domain.product.ProductSellingType.SELLING;
import static sample.cafekiosk.domain.product.ProductType.HANDMADE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sample.cafekiosk.IntegrationTestSupport;
import sample.cafekiosk.domain.ProductRepository;
import sample.cafekiosk.domain.mail.MailSendHistory;
import sample.cafekiosk.domain.mail.MailSendHistoryRepository;
import sample.cafekiosk.domain.order.Order;
import sample.cafekiosk.domain.order.OrderRepository;
import sample.cafekiosk.domain.order.OrderStatus;
import sample.cafekiosk.domain.orderproduct.OrderProductRepository;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductType;

class OrderStatisticServiceTest extends IntegrationTestSupport {

    @Autowired
    private OrderStatisticService orderStatisticService;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MailSendHistoryRepository mailSendHistoryRepository;

    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        mailSendHistoryRepository.deleteAllInBatch();
    }

    @DisplayName("결제완료 주문들을 조회하여 매출 통계 이메일을 발송한다.")
    @Test
    void test() {
        // given
        LocalDateTime now = LocalDateTime.of(2023, 6, 3, 0, 0, 0);

        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 2000);
        Product product3 = createProduct(HANDMADE, "003", 3000);
        List<Product> products = List.of(product1, product2, product3);
        productRepository.saveAll(products);

        Order order1 = createPayCompletedOrder(LocalDateTime.of(2023, 6, 2, 23, 59, 59), products);
        Order order2 = createPayCompletedOrder(now, products);
        Order order3 = createPayCompletedOrder(LocalDateTime.of(2023, 6, 3, 23, 59, 59), products);
        Order order4 = createPayCompletedOrder(LocalDateTime.of(2023, 6, 4, 0, 0, 0), products);

        // stubbing
        when(mailSendClient.sendMail(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(true);

        // when
        boolean result = orderStatisticService.sendOrderStatisticMail(LocalDate.of(2023, 6, 3), "test@cafekiosk.com");

        // then
        assertThat(result).isTrue();
        List<MailSendHistory> histories = mailSendHistoryRepository.findAll();
        assertThat(histories).hasSize(1)
            .extracting("content")
            .contains("총 매출 합계는 12000원 입니다.");

    }

    private Order createPayCompletedOrder(LocalDateTime now, List<Product> products) {
        Order order = Order.builder()
            .products(products)
            .orderStatus(OrderStatus.PAYMENT_COMPLETED)
            .registeredDateTime(now)
            .build();

        return orderRepository.save(order);
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