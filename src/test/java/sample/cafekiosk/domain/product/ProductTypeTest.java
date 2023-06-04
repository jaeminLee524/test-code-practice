package sample.cafekiosk.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

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

    @DisplayName("CsvSource와 ParameterizedTest를 이용해 상품 타입이 재고와 관련된 타입인지 체크한다.")
    @CsvSource({"HANDMADE,false", "BOTTLE,true", "BAKERY,true"})
    @ParameterizedTest
    void containsEnableStockType3(ProductType productType, boolean expected) {
        // when
        boolean result = ProductType.containsEnableStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideProductTypeForCheckingStockType() {
        return Stream.of(
            Arguments.of(ProductType.HANDMADE, false),
            Arguments.of(ProductType.BOTTLE, true),
            Arguments.of(ProductType.BAKERY, true)
        );
    }

    @DisplayName("MethodSource, ParameterizedTest를 이용해 상품 타입이 재고와 관련된 타입인지 체크한다.")
    @MethodSource("provideProductTypeForCheckingStockType")
    @ParameterizedTest
    void containsEnableStockType4(ProductType productType, boolean expected) {
        // when
        boolean result = ProductType.containsEnableStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }
}