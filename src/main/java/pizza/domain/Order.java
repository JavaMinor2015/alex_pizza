package pizza.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/4/15.
 */
@Getter
@Setter
public class Order {
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(final OrderItem item) {
        orderItems.add(item);
    }
}
