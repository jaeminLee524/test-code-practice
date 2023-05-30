package sample.cafekiosk.domain.product;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {

    HANDMADE("제조 음료"),
    BOTTLE("병 음료"),
    BAKERY("베이커리"),
    ;

    private final String description;

    public static boolean containsEnableStockType(ProductType type) {
        return List.of(BOTTLE, BAKERY).contains(type);
    }
}
