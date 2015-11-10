package pizza.repository;

import lombok.Getter;
import lombok.Setter;
import pizza.domain.concrete.persist.PizzaOrder;
import pizza.repository.abs.Repository;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.List;

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

}
