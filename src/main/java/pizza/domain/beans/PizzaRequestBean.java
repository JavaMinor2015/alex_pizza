package pizza.domain.beans;

import pizza.domain.concrete.persist.PizzaOrder;
import pizza.domain.concrete.persist.OrderItem;
import pizza.domain.concrete.persist.Pizza;
import pizza.repository.OrderRepository;
import pizza.repository.PizzaRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Stateful
public class PizzaRequestBean implements Serializable {

    @Inject
    PizzaRepository pizzaRepository;

    @Inject
    OrderRepository orderRepository;

    /**
     * Add a pizza to this bean.
     *
     * @param pizza the pizza to add
     */
    public void addPizza(final Pizza pizza) {
        pizzaRepository.add(pizza);
    }

    /**
     * Add an order to this bean.
     *
     * The order will be added and persisted.
     *
     * @param pizzaOrder the order to add
     */
    public void addOrder(final PizzaOrder pizzaOrder) {
        orderRepository.addItem(stripEmptyOrders(pizzaOrder));
        orderRepository.save();
    }

    /**
     * Returns all known pizza's in this bean.
     *
     * @return all known pizza's
     */
    public List<Pizza> getAll() {
        pizzaRepository.load();
        return pizzaRepository.getAll();
    }

    private PizzaOrder stripEmptyOrders(final PizzaOrder pizzaOrder) {
        Iterator<OrderItem> it = pizzaOrder.getOrderItems().iterator();
        while(it.hasNext()){
            if(it.next().getAmount() < 1) {
                it.remove();
            }
        }
        return pizzaOrder;
    }

    public List<PizzaOrder> getOrders() {
        return orderRepository.getPizzaOrders();
    }
}
