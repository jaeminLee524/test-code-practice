package sample.cafekiosk.api.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.api.service.product.ProductResponse;
import sample.cafekiosk.domain.ProductRepository;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductSellingType;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingTypeIn(ProductSellingType.forDisplay());

        return products.stream()
            .map(product -> ProductResponse.of(product))
            .collect(Collectors.toList());
    }
}
