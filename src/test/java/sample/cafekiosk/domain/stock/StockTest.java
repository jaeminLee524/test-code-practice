package sample.cafekiosk.domain.stock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class StockTest {

    private static final Stock stock = Stock.create("001", 1);

    @DisplayName("가지고 있는 재고 수량이 요청받은 재고 수량보다 적은지 확인한다.")
    @Test
    void isQuantityLessThan2() {
        // given
        int quantity = 2;

        // when
        boolean result = stock.isQuantityLessThan(quantity);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("요청한 재고만큼 기존 재고 수량을 차감한다.")
    @Test
    void deductQuantity3() {
        // given
        int quantity = 1;

        // when
        stock.deductQuantity(quantity);

        // then
        assertThat(stock.getQuantity()).isZero();
    }

    @DisplayName("가지고 있는 재고 수량이 요청받은 재고 수량보다 적은지 확인한다.")
    @Test
    void isQuantityLessThan() {
        // given
        Stock stock = Stock.create("001", 2);
        int quantity = 1;

        // when
        boolean result = stock.isQuantityLessThan(quantity);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("요청한 재고만큼 기존 재고 수량을 차감한다.")
    @Test
    void deductQuantity() {
        // given
        Stock stock = Stock.create("001", 2);
        int quantity = 1;

        // when
        stock.deductQuantity(quantity);

        // then
        assertThat(stock.getQuantity()).isOne();
    }

    @DisplayName("가지고 있는 수량보다 기존 재고의 개수가 적은 경우 throw를 발생시킨다.")
    @Test
    void deductQuantity2() {
        // given
        Stock stock = Stock.create("001", 2);
        int quantity = 3;

        // when &&  then
        assertThatThrownBy(() -> stock.deductQuantity(quantity))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("재고가 부족하여 수량 감소를 할 수 없습니다.");
    }

    @DisplayName("재고 차감 시나리오")
    @TestFactory
    Collection<DynamicTest> stockDeductionDynamicTest() {
        // given
        Stock stock = Stock.create("001", 1);

        return List.of(
            DynamicTest.dynamicTest("재고를 주어진 개수만큼 차감할 수 았다.", () -> {
                // given
                int quantity = 1;

                // when
                stock.deductQuantity(quantity);

                // then
                assertThat(stock.getQuantity()).isZero();
            }),
            DynamicTest.dynamicTest("재고보다 많은 수의 수량으로 차감 시도하는 경우 예외가 발생한다.", () -> {
                // given
                int quantity = 1;

                // when // then
                assertThatThrownBy(() -> stock.deductQuantity(quantity))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("재고가 부족하여 수량 감소를 할 수 없습니다.");
            })
        );
    }
}