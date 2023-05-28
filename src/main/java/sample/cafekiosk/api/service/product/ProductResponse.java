package sample.cafekiosk.api.service.product;

import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductSellingType;
import sample.cafekiosk.domain.product.ProductType;

@Getter
public class ProductResponse {

    private Long id;
    private String productNumber;
    private ProductType type;
    private ProductSellingType sellingType;
    private String name;
    private int price;

    @Builder
    public ProductResponse(Long id, String productNumber, ProductType type, ProductSellingType sellingType, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.sellingType = sellingType;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .productNumber(product.getProductNumber())
            .type(product.getType())
            .sellingType(product.getSellingType())
            .name(product.getName())
            .price(product.getPrice())
            .build();
    }
}
