package sample.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import sample.unit.beverage.Americano;
import sample.unit.beverage.Latte;

class CafeKioskTest {

    @Test
    void add_manual_test() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        System.out.println("음료가 담긴 총 개수: " + cafeKiosk.getBeverageList().size());
        System.out.println("키오스크에 담긴 음료: " + cafeKiosk.getBeverageList().get(0).getName());
    }

    @Test
    void addSeveralBeverage() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano, 2);

        assertThat(cafeKiosk.getBeverageList()).hasSize(2);
        assertThat(cafeKiosk.getBeverageList().get(0).getName()).isEqualTo("아메리카노");
        assertThat(cafeKiosk.getBeverageList().get(1).getName()).isEqualTo("아메리카노");
    }

    @Test
    void addZeroBeverage() {
        CafeKiosk cafeKiosk = new CafeKiosk();

        assertThatThrownBy(() -> cafeKiosk.add(new Americano(), 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("음료는 1잔 이상의 경우에만 주문하실 수 있습니다.");
    }

    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        assertThat(cafeKiosk.getBeverageList().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverageList()).hasSize(1);
        assertThat(cafeKiosk.getBeverageList().get(0).getName()).isEqualTo("아메리카노");
        assertThat(cafeKiosk.getBeverageList().get(0).getPrice()).isEqualTo(4000);
    }

    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        assertThat(cafeKiosk.getBeverageList()).hasSize(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverageList()).hasSize(0);
        assertThat(cafeKiosk.getBeverageList()).isEmpty();
    }

    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.addAll(List.of(new Americano(), new Latte()));

        assertThat(cafeKiosk.getBeverageList()).hasSize(2);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverageList()).hasSize(0);
        assertThat(cafeKiosk.getBeverageList()).isEmpty();
    }
}