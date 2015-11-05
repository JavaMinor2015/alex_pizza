package pizza.repository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pizza.domain.concrete.persist.PizzaOrder;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    private static final long serialVersionUID = -3266726493781699451L;

    @PersistenceContext
    private EntityManager em;

    @Getter(AccessLevel.NONE)
    private List<PizzaOrder> pizzaOrders = new ArrayList<>();

    public List<PizzaOrder> getPizzaOrders() {
        load();
        return pizzaOrders;
    }

    /**
     * Add an order to this repository.
     *
     * @param pizzaOrder the order to add
     */
    public void addItem(final PizzaOrder pizzaOrder) {
        pizzaOrders.add(pizzaOrder);
    }

    /**
     * Load state from persistence.
     */
    public void load() {
        // magical persistence here
//        Query query = em.createQuery("SELECT o FROM " + PizzaOrder.class.getSimpleName() + " o");
//        pizzaOrders = (List<PizzaOrder>) query.getResultList();
        pizzaOrders = new ArrayList<>();
        pizzaOrders.add(
                em.find(PizzaOrder.class, 0)
        );
    }

    /**
     * Save state to persistence.
     */
    public void save() {
        pizzaOrders.forEach(em::persist);
        em.flush();
    }
}
