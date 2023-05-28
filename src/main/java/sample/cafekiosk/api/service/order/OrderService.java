package sample.cafekiosk.api.service.order;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.api.service.order.response.OrderResponse;
import sample.cafekiosk.domain.ProductRepository;
import sample.cafekiosk.domain.order.Order;
import sample.cafekiosk.domain.order.OrderRepository;
import sample.cafekiosk.domain.product.Product;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
        List<String> productNumbers = request.getProductNumbers();

        // find Products
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        // create order
        Order order = Order.create(products, registeredDateTime);

        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }
}
