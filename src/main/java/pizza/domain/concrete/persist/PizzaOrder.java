package pizza.domain.concrete.persist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pizza.domain.concrete.persist.abs.PersistentEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/4/15.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class PizzaOrder extends PersistentEntity implements Serializable {

    private static final long serialVersionUID = 8151307223913811802L;

    @OneToMany(cascade = CascadeType.ALL)
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
