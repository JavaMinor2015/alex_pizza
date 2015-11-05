package pizza.domain.beans;

import pizza.domain.concrete.Order;
import pizza.domain.concrete.OrderItem;
import pizza.domain.concrete.Pizza;
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
    private PizzaRepository pizzaRepository;

    @Inject
    private OrderRepository orderRepository;

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
     * @param order the order to add
     */
    public void addOrder(final Order order) {
        orderRepository.addItem(stripEmptyOrders(order));
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

    private Order stripEmptyOrders(final Order order) {
        Iterator<OrderItem> it = order.getOrderItems().iterator();
        while(it.hasNext()){
            if(it.next().getAmount() < 1) {
                it.remove();
            }
        }
        return order;
    }
}
