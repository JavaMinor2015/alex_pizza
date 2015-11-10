package pizza.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pizza.domain.beans.PizzaRequestBean;
import pizza.domain.concrete.persist.OrderItem;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.PizzaOrder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Named("orderService")
@RequestScoped
@Getter
@Setter
public class OrderService implements Serializable {

    private static final long serialVersionUID = -9167173232808280905L;

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class.getName());

    private String orderStatus;
    private List<Pizza> pizzas;
    private PizzaOrder pizzaOrder;

    private List<PizzaOrder> orders;

    @EJB
    private PizzaRequestBean pizzaBean;

    /**
     * Initializes the service after bean injection.
     */
    @PostConstruct
    public void init() {
        if (pizzas == null) {
            pizzas = pizzaBean.getAll();
            pizzaOrder = new PizzaOrder();
            for (Pizza pizza : pizzas) {
                pizzaOrder.add(new OrderItem(pizza, 0));
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
        for (OrderItem orderItem : pizzaOrder.getOrderItems()) {
            builder.append(orderItem.getPizza().getName())
                    .append(" @ ")
                    .append(orderItem.getAmount())
                    .append(" \n");
        }
        orderStatus = builder.toString();
        pizzaBean.addOrder(pizzaOrder);
    }

    /**
     * Initialize the orders.
     */
    public void initOrders() {
        orders = pizzaBean.getOrders();
    }

    public Pizza findById(final Long id) {
        return pizzaBean.findById(id);
    }
}
