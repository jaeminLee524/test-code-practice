package sample.cafekiosk.api.controller.product;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk.api.controller.product.dto.ProductCreateRequest;
import sample.cafekiosk.api.service.ProductService;
import sample.cafekiosk.api.service.product.ProductResponse;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/v1/products/new")
    public ProductResponse createProducts(
        @RequestBody ProductCreateRequest request
    ) {
        return productService.createProducts(request);
    }

    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> getSellingProducts() {
        return productService.getSellingProducts();
    }
}
