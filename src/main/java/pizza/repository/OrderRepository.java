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

    public void addItem(final Order order) {
        orders.add(order);
    }

    public void load(){
        // magical persistence here
    }

    public void save(){
        // magical persistence here
    }
}
