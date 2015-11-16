package pizza.repository;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import lombok.Getter;
import lombok.Setter;
import pizza.domain.concrete.persist.PizzaOrder;
import pizza.repository.abs.Repository;

/**
 * Created by alex on 11/4/15.
 */
@Stateful
@Getter
@Setter
public class OrderRepository extends Repository<PizzaOrder> implements Serializable {

    private static final long serialVersionUID = -3266726493781699451L;

    @Override
    public List<PizzaOrder> getAll() {
        return getAll(PizzaOrder.class);
    }

    /**
     * Find a pizza order by its id.
     *
     * @param id the id of the order.
     * @return the order, null if not found.
     */
    public PizzaOrder findById(final Long id) {
        return super.findById(PizzaOrder.class, id);
    }
}
