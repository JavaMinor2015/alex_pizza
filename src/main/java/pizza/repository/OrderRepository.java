package pizza.repository;

import lombok.Getter;
import lombok.Setter;
import pizza.domain.concrete.Order;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/4/15.
 */
@Stateful
@Getter
@Setter
public class OrderRepository implements Serializable {

    private List<Order> orders = new ArrayList<>();

    /**
     * Add an order to this repository.
     *
     * @param order the order to add
     */
    public void addItem(final Order order) {
        orders.add(order);
    }

    /**
     * Load state from persistence.
     */
    public void load(){
        // magical persistence here
    }

    /**
     * Save state to persistence.
     */
    public void save(){
        // magical persistence here
    }
}
