package sample.cafekiosk.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTypeTest {

    @DisplayName("상품 타입이 재고와 관련된 타입인지 체크한다.(false)")
    @Test
    void containsEnableStockType1() {
        // given
        ProductType bakery = ProductType.HANDMADE;

        // when
        boolean result = ProductType.containsEnableStockType(bakery);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("상품 타입이 재고와 관련된 타입인지 체크한다.(true)")
    @Test
    void containsEnableStockType2() {
        // given
        ProductType bottle = ProductType.BOTTLE;

        // when
        boolean result = ProductType.containsEnableStockType(bottle);

        // then
        assertThat(result).isTrue();
    }
}