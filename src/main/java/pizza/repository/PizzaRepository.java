package pizza.repository;

import lombok.Getter;
import lombok.Setter;
import pizza.domain.concrete.persist.Pizza;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Stateful
@Getter
@Setter
public class PizzaRepository implements Serializable {

    private static final long serialVersionUID = -8756675831989435083L;

    @PersistenceContext(unitName = "PizzaPersist")
    private EntityManager em;

    private List<Pizza> pizzaList = new ArrayList<>();

    /**
     * Add a pizza to this repository.
     *
     * @param pizza the pizza to add
     */
    public void add(final Pizza pizza) {
        pizzaList.add(pizza);
    }

    /**
     * Retrieve all pizzas.
     * <p>
     * {@link #load()} not called
     *
     * @return a list of pizzas
     */
    public List<Pizza> getAll() {
        load();
        return pizzaList;
    }

    /**
     * Load state from persistence.
     */
    public void load() {
        Query query = em.createQuery("SELECT o FROM " + Pizza.class.getSimpleName() + " o");
        pizzaList = (List<Pizza>) query.getResultList();
    }

    /**
     * Save state to persistence.
     */
    public void save() {
        pizzaList.forEach(em::persist);
        em.flush();
    }


}
