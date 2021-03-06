package pizza.repository;

import lombok.Getter;
import lombok.Setter;
import pizza.domain.concrete.persist.Pizza;
import pizza.repository.abs.Repository;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Stateful
@Getter
@Setter
public class PizzaRepository extends Repository<Pizza> implements Serializable {

    private static final long serialVersionUID = -8756675831989435083L;

    /**
     * Find a pizza by its id.
     *
     * @param id the id to search for
     * @return the corresponding pizza or null
     */
    public Pizza findById(final Long id) {
        return super.findById(Pizza.class, id);
    }

    @Override
    public List<Pizza> getAll() {
        return super.getAll(Pizza.class);
    }
}
