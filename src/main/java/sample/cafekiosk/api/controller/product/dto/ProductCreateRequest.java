package sample.cafekiosk.api.controller.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductSellingType;
import sample.cafekiosk.domain.product.ProductType;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    private ProductType type;
    private ProductSellingType sellingType;
    private String name;
    private int price;

    @Builder
    private ProductCreateRequest(ProductType type, ProductSellingType sellingType, String name, int price) {
        this.type = type;
        this.sellingType = sellingType;
        this.name = name;
        this.price = price;
    }

    public Product toEntity(String productNumber) {
        return Product.builder()
            .productNumber(productNumber)
            .type(this.type)
            .sellingType(this.sellingType)
            .name(this.name)
            .price(this.price)
            .build();
    }
}
