package pizza.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pizza.domain.Order;
import pizza.domain.OrderItem;
import pizza.domain.Pizza;
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
@Named
@SessionScoped
@Getter
@Setter
public class OrderService implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class.getName());

    private String orderStatus;
    private List<Pizza> pizzas;
    private Order order;

    @EJB
    PizzaRequestBean pizzaBean;

    @PostConstruct
    public void init() {
        if(pizzas == null) {
            pizzas = pizzaBean.getAll();
            order = new Order();
            for (Pizza pizza : pizzas) {
                order.add(new OrderItem(pizza, 0));
            }
        }
    }

    public void order() {
        orderStatus = "";
        for (OrderItem orderItem : order.getOrderItems()) {
            orderStatus += orderItem.getPizza().getName() + " @ " + orderItem.getAmount() + " \n";
        }
        pizzaBean.addOrder(order);
    }
}
