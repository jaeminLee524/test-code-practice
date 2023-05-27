package sample.unit;

import java.util.List;
import org.assertj.core.api.Assertions;
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
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        Assertions.assertThat(cafeKiosk.getBeverageList().size()).isEqualTo(1);
        Assertions.assertThat(cafeKiosk.getBeverageList()).hasSize(1);
        Assertions.assertThat(cafeKiosk.getBeverageList().get(0).getName()).isEqualTo("아메리카노");
        Assertions.assertThat(cafeKiosk.getBeverageList().get(0).getPrice()).isEqualTo(4000);
    }

    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        Assertions.assertThat(cafeKiosk.getBeverageList()).hasSize(1);

        cafeKiosk.remove(americano);
        Assertions.assertThat(cafeKiosk.getBeverageList()).hasSize(0);
        Assertions.assertThat(cafeKiosk.getBeverageList()).isEmpty();
    }

    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.addAll(List.of(new Americano(), new Latte()));

        Assertions.assertThat(cafeKiosk.getBeverageList()).hasSize(2);

        cafeKiosk.clear();
        Assertions.assertThat(cafeKiosk.getBeverageList()).hasSize(0);
        Assertions.assertThat(cafeKiosk.getBeverageList()).isEmpty();
    }
}