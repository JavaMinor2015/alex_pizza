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

    /**
     * Add an order item to this order.
     *
     * @param orderItem the order item to add
     */
    public void add(final OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    /**
     * Removes an order item from the list of items.
     *
     * @param orderItem the item to remove
     */
    public void remove(final OrderItem orderItem) {
        orderItems.remove(orderItem);
    }
}
