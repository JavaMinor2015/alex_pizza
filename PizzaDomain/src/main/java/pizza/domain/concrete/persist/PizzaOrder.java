package pizza.domain.concrete.persist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pizza.domain.concrete.persist.abs.PersistentEntity;

/**
 * Created by alex on 11/4/15.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class PizzaOrder extends PersistentEntity implements Serializable {

    private static final long serialVersionUID = 8151307223913811802L;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar orderTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

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
