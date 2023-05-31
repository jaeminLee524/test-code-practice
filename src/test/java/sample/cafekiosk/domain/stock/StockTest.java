package sample.cafekiosk.domain.stock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockTest {

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
}