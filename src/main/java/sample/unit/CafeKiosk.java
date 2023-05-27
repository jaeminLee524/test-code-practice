package sample.unit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Getter;
import sample.unit.beverage.Beverage;
import sample.unit.order.Order;

@Getter
public class CafeKiosk {

    private final List<Beverage> beverageList = new ArrayList<>();
    public void add(Beverage beverage) {
        beverageList.add(beverage);
    }

    public void add(Beverage beverage, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("음료는 1잔 이상의 경우에만 주문하실 수 있습니다.");
        }

        for (int i = 0; i < count; i++) {
            beverageList.add(beverage);
        }
    }

    public void addAll(List<Beverage> beverages) {
        beverageList.addAll(beverages);
    }

    public void remove(Beverage beverage) {
        beverageList.remove(beverage);
    }

    public void clear() {
        beverageList.clear();
    }

    public int calculateTotalPrice() {
        return beverageList.stream()
            .mapToInt(Beverage::getPrice)
            .sum();
    }

    public Order createOrder() {
        return new Order(LocalDateTime.now(), this.beverageList);
    }
}
