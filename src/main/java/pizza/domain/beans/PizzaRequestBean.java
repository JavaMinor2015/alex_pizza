package pizza.domain.beans;

import pizza.domain.concrete.Order;
import pizza.domain.concrete.Pizza;
import pizza.repository.OrderRepository;
import pizza.repository.PizzaRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;
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

    public void addPizza(final Pizza pizza) {
        pizzaRepository.add(pizza);
    }

    public void addOrder(final Order order) {
        orderRepository.addItem(stripEmptyOrders(order));
    }

    public List<Pizza> getAll() {
        pizzaRepository.load();
        return pizzaRepository.getAll();
    }

    private Order stripEmptyOrders(final Order order) {
        order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getAmount() < 1)
                .forEach(order::remove);
        return order;
    }
}
