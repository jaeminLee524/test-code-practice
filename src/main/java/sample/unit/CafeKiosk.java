package sample.unit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import sample.unit.beverage.Beverage;
import sample.unit.order.Order;

@Getter
public class CafeKiosk {

    private final List<Beverage> beverageList = new ArrayList<>();
    public void add(Beverage beverage) {
        beverageList.add(beverage);
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
