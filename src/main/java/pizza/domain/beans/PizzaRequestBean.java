package pizza.domain.beans;

import lombok.Getter;
import lombok.Setter;
import pizza.domain.concrete.persist.Delivery;
import pizza.domain.concrete.persist.OrderItem;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.PizzaOrder;
import pizza.repository.DeliveryRepository;
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
@Getter
@Setter
public class PizzaRequestBean implements Serializable {

    private static final long serialVersionUID = -4540135125666933872L;

    @Inject
    PizzaRepository pizzaRepository;

    @Inject
    OrderRepository orderRepository;

    @Inject
    DeliveryRepository deliveryRepository;

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
     * <p>
     * The order will be added and persisted.
     *
     * @param pizzaOrder the order to add
     */
    public void addOrder(final PizzaOrder pizzaOrder) {
        orderRepository.add(stripEmptyOrders(pizzaOrder));
        orderRepository.save();
        // note, empty orders have been stripped
        upgradeInventory(pizzaOrder);
        Delivery delivery = deliveryRepository.createDeliveryForOrder(pizzaOrder);
        deliveryRepository.add(delivery);
        deliveryRepository.save();
    }

    private void upgradeInventory(final PizzaOrder pizzaOrder) {
        for (OrderItem orderItem : pizzaOrder.getOrderItems()) {
            long old = orderItem.getPizza().getAmountSold();
            long oldTotal = orderItem.getPizza().getAmountSoldTotal();
            // TODO replace with database trigger
            orderItem.getPizza().setAmountSold(old + orderItem.getAmount());
            orderItem.getPizza().setAmountSoldTotal(oldTotal + orderItem.getAmount());
        }
    }

    /**
     * Returns all known pizza's in this bean.
     *
     * @return all known pizza's
     */
    public List<Pizza> getAll() {
        return pizzaRepository.getAll();
    }

    private PizzaOrder stripEmptyOrders(final PizzaOrder pizzaOrder) {
        Iterator<OrderItem> it = pizzaOrder.getOrderItems().iterator();
        while (it.hasNext()) {
            if (it.next().getAmount() < 1) {
                it.remove();
            }
        }
        return pizzaOrder;
    }

    /**
     * Get all orders.
     *
     * @return all orders or an empty list
     */
    public List<PizzaOrder> getOrders() {
        return orderRepository.getAll();
    }

    /**
     * Find a pizza by its id.
     *
     * @param id the id to search for
     * @return the corresponding pizza or null
     */
    public Pizza findById(final Long id) {
        return pizzaRepository.findById(id);
    }

    /**
     * Retrieves all deliveries.
     *
     * @return a list of deliveries or an empty list.
     */
    public List<Delivery> getDeliveries() {
        return deliveryRepository.getAll();
    }
}
