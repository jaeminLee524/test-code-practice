package sample.cafekiosk.api.service;

import static sample.cafekiosk.domain.product.ProductSellingType.SELLING;
import static sample.cafekiosk.domain.product.ProductType.HANDMADE;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.api.controller.product.dto.ProductCreateRequest;
import sample.cafekiosk.api.service.product.ProductResponse;
import sample.cafekiosk.domain.ProductRepository;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductSellingType;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProducts(ProductCreateRequest request) {
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);

        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    private String createNextProductNumber() {
        String productNumber = productRepository.findLatestProductNumber();
        if (Objects.isNull(productNumber)) {
            return "001";
        }

        int nextProductNumber = Integer.parseInt(productNumber) + 1;
        return String.format("%03d", nextProductNumber);
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingTypeIn(ProductSellingType.forDisplay());

        return products.stream()
            .map(product -> ProductResponse.of(product))
            .collect(Collectors.toList());
    }
}
