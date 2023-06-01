package sample.cafekiosk.api.service;

import static org.assertj.core.api.Assertions.*;
import static sample.cafekiosk.domain.product.ProductSellingType.HOLD;
import static sample.cafekiosk.domain.product.ProductSellingType.SELLING;
import static sample.cafekiosk.domain.product.ProductSellingType.STOP_SELLING;
import static sample.cafekiosk.domain.product.ProductType.HANDMADE;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.api.controller.product.request.ProductCreateRequest;
import sample.cafekiosk.api.service.product.ProductResponse;
import sample.cafekiosk.domain.ProductRepository;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductSellingType;
import sample.cafekiosk.domain.product.ProductType;

@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품 번호에서 1증가한 값이다.")
    @Test
    void createProduct() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);

        productRepository.saveAll(List.of(product1, product2, product3));

        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(HANDMADE)
            .sellingType(SELLING)
            .name("카푸치노")
            .price(5000)
            .build();

        // when
        ProductResponse productResponse = productService.createProducts(request.toServiceDto());

        // then
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingType", "name", "price")
            .contains("004", HANDMADE, SELLING, "카푸치노", 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(4)
            .extracting("productNumber", "type", "sellingType", "name", "price")
            .containsExactlyInAnyOrder(
                tuple("001", HANDMADE, SELLING, "아메리카노", 4000),
                tuple("002", HANDMADE, HOLD, "카페라떼", 4500),
                tuple("003", HANDMADE, STOP_SELLING, "팥빙수", 7000),
                tuple("004", HANDMADE, SELLING, "카푸치노", 5000)
            );

    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록한다면, 상품 번호는 001이다.")
    @Test
    void createProductWhenProductIsEmpty() {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(HANDMADE)
            .sellingType(SELLING)
            .name("카푸치노")
            .price(5000)
            .build();

        // when
        ProductResponse productResponse = productService.createProducts(request.toServiceDto());

        // then
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingType", "name", "price")
            .contains("001", request.getType(), request.getSellingType(), request.getName(), request.getPrice());

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1)
            .extracting("productNumber", "type", "sellingType", "name", "price")
            .containsExactlyInAnyOrder(
                tuple("001", HANDMADE, SELLING, "카푸치노", 5000)
            );
    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingType sellingType, String name, int price) {
        return Product.builder()
            .productNumber(productNumber)
            .type(type)
            .sellingType(sellingType)
            .name(name)
            .price(price)
            .build();
    }
}