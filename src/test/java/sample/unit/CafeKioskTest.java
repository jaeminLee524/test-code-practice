package sample.unit;

import org.junit.jupiter.api.Test;
import sample.unit.beverage.Americano;

class CafeKioskTest {

    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        System.out.println("음료가 담긴 총 개수: " + cafeKiosk.getBeverageList().size());
        System.out.println("키오스크에 담긴 음료: " + cafeKiosk.getBeverageList().get(0).getName());
    }
}