package service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import pizza.domain.beans.PizzaRequestBean;
import pizza.domain.concrete.persist.Delivery;
import pizza.domain.concrete.persist.OrderItem;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.Address;
import pizza.domain.concrete.persist.PizzaOrder;

/**
 * Created by alex on 11/3/15.
 */
@Named("orderService")
@RequestScoped
public class OrderService implements Serializable {

    private static final long serialVersionUID = -9167173232808280905L;


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
            pizzaOrder.setAddress(new Address());
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
        pizzaOrder.setOrderTime(Calendar.getInstance());
        pizzaBean.addOrder(pizzaOrder);
    }

    /**
     * Initialize the orders.
     */
    public void initOrders() {
        orders = pizzaBean.getOrders();
    }

    /**
     * Find a pizza by its id.
     *
     * @param id the id to search for
     * @return the corresponding pizza or null
     */
    public Pizza findById(final Long id) {
        return pizzaBean.findById(id);
    }

    /**
     * Fetches orders from the repository.
     *
     * @return a list of orders, or an empty list.
     */
    public List<PizzaOrder> getOrders() {
        return pizzaBean.getOrders();
    }

    /**
     * Fetches deliveries from the repository.
     *
     * @return a list of deliveries, or an empty list.
     */
    public List<Delivery> getDeliveries() {
        return pizzaBean.getDeliveries();
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public List<Pizza> getPizzas() {
        return this.pizzas;
    }

    public PizzaOrder getPizzaOrder() {
        return this.pizzaOrder;
    }

    public PizzaRequestBean getPizzaBean() {
        return this.pizzaBean;
    }

    public void setOrderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPizzas(final List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void setPizzaOrder(final PizzaOrder pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
    }

    public void setOrders(final List<PizzaOrder> orders) {
        this.orders = orders;
    }

    public void setPizzaBean(final PizzaRequestBean pizzaBean) {
        this.pizzaBean = pizzaBean;
    }
}
