package pizza.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pizza.domain.concrete.Order;
import pizza.domain.concrete.OrderItem;
import pizza.domain.concrete.Pizza;
import pizza.domain.beans.PizzaRequestBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Named("orderService")
@SessionScoped
@Getter
@Setter
public class OrderService implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class.getName());

    private String orderStatus;
    private List<Pizza> pizzas;
    private Order order;

    @EJB
    private PizzaRequestBean pizzaBean;

    /**
     * Initializes the service after bean injection.
     */
    @PostConstruct
    public void init() {
        if (pizzas == null) {
            pizzas = pizzaBean.getAll();
            order = new Order();
            for (Pizza pizza : pizzas) {
                order.add(new OrderItem(pizza, 0));
            }
        }
    }

    /**
     * Finalize an order.
     * <p>
     * The order will be added to the bean and persisted.
     */
    public void order() {
        StringBuilder builder = new StringBuilder();
        for (OrderItem orderItem : order.getOrderItems()) {
            builder.append(orderItem.getPizza().getName() + " @ " + orderItem.getAmount() + " \n");
        }
        orderStatus = builder.toString();
        pizzaBean.addOrder(order);
    }
}
