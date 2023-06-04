package sample.cafekiosk.api.service.order;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.cafekiosk.api.service.mail.MailService;
import sample.cafekiosk.domain.order.Order;
import sample.cafekiosk.domain.order.OrderRepository;
import sample.cafekiosk.domain.order.OrderStatus;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderStatisticService {

    private final OrderRepository orderRepository;
    private final MailService mailService;

    public boolean sendOrderStatisticMail(LocalDate orderDate, String email) {
        // 주문 일자에 해당하는 주문 정보들을 가져오고
        List<Order> orders = orderRepository.findOrdersBy(
            orderDate.atStartOfDay(),
            orderDate.plusDays(1).atStartOfDay(),
            OrderStatus.PAYMENT_COMPLETED
        );

        // 주문 정보들의 가격을 더해서
        int totalAmount = orders.stream()
            .mapToInt(Order::getTotalPrice)
            .sum();

        log.info("totalPrice: {}", totalAmount);

        // 이메일을 보낸다.
        boolean result = mailService.sendMail(
            "no-reply@cafekiosk.com",
            email,
            String.format("[매출통계] %s", orderDate),
            String.format("총 매출 합계는 %s원 입니다.", totalAmount)
        );

        if (!result) {
            throw new IllegalArgumentException("메일 전송 과정에서 문제가 발생하였습니다.");
        }

        return true;
    }
}
