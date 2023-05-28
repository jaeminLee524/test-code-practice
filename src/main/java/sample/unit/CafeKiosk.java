package sample.unit;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import sample.unit.beverage.Beverage;
import sample.unit.order.Order;

@Getter
public class CafeKiosk {

    private static final LocalTime OPEN_TIME = LocalTime.of(10, 0);
    private static final LocalTime CLOSE_TIME = LocalTime.of(22, 0);
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
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        if (currentTime.isBefore(OPEN_TIME) || currentTime.isAfter(CLOSE_TIME)) {
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.");
        }

        return new Order(currentDateTime, this.beverageList);
    }

    public Order createOrder(LocalDateTime currentDateTime) {
        LocalTime currentTime = currentDateTime.toLocalTime();
        if (currentTime.isBefore(OPEN_TIME) || currentTime.isAfter(CLOSE_TIME)) {
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.");
        }

        return new Order(currentDateTime, this.beverageList);
    }
}
