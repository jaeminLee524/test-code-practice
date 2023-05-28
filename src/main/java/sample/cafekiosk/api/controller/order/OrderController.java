package sample.cafekiosk.api.controller.order;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.api.service.order.OrderService;
import sample.cafekiosk.api.service.order.response.OrderResponse;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public OrderResponse createOrder(
        @RequestBody OrderCreateRequest request
    ) {
        LocalDateTime registeredDateTime = LocalDateTime.now();

        return orderService.createOrder(request, registeredDateTime);
    }
}
