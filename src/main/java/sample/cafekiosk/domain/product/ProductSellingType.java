package sample.cafekiosk.domain.product;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductSellingType {

    SELLING("판매중"),
    HOLD("판매보류"),
    STOP_SELLING("판매중지"),
    ;

    private final String description;

    public static List<ProductSellingType> forDisplay() {
        return List.of(SELLING, HOLD);
    }
}
