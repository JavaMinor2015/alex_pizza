package pizza.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pizza.domain.Order;
import pizza.domain.Pizza;
import pizza.domain.beans.ConfigBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Named
@SessionScoped
@Getter
@Setter
public class OrderService implements Serializable{

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class.getName());

    private String orderStatus;
    private List<Pizza> pizzas;
    private Order order;

    @EJB
    ConfigBean pizzaBean;

    public void init(){
        pizzas = pizzaBean.getPizzaRequestBean().getAll();
        order = new Order();
        order.setPizzaList(pizzas);
        List<Integer> amounts = new ArrayList<>();
        for (Pizza pizza : pizzas) {
            amounts.add(0);
        }
        order.setAmounts(amounts);
    }

    public List<Pizza> getAllPizzas() {
        if(pizzas == null) {
            init();
        }
        return pizzas;
    }

    public Pizza get(final String pizzaName) {
        for (Pizza c : pizzas) {
            if (c.getName().equals(pizzaName)) {
                return c;
            }
        }
        return pizzas.get(0);
    }

    public void order(){
        orderStatus = "";
        for (int i = 0; i < order.getAmounts().size(); i++) {
            orderStatus += order.getPizzaList().get(i).getName();
            orderStatus += "@";
            orderStatus += order.getAmounts().get(i);
            orderStatus += "\n";
        }
    }
}
