package pizza.domain.concrete;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/4/15.
 */
@Getter
@Setter
public class Order implements Serializable {
    private List<OrderItem> orderItems = new ArrayList<>();

    public void add(final OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public void remove(final OrderItem orderItem) {
        orderItems.remove(orderItem);
    }
}
