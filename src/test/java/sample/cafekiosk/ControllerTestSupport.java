package sample.cafekiosk;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sample.cafekiosk.api.controller.order.OrderController;
import sample.cafekiosk.api.controller.product.ProductController;
import sample.cafekiosk.api.service.ProductService;
import sample.cafekiosk.api.service.order.OrderService;

@WebMvcTest(controllers = {
    OrderController.class,
    ProductController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    // bean으로 관뢰되고 있는 대상을 MockBean으로 생성해 Container 에서 관리
    @MockBean
    protected OrderService orderService;

    // bean으로 관뢰되고 있는 대상을 MockBean으로 생성해 Container 에서 관리
    @MockBean
    protected ProductService productService;
}
