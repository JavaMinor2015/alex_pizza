package pizza.repository;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import pizza.domain.concrete.persist.Delivery;
import pizza.domain.concrete.persist.PizzaOrder;
import pizza.repository.abs.Repository;
import pizza.rules.BusinessRules;

/**
 * Created by alex on 11/10/15.
 */
@Stateless
public class DeliveryRepository extends Repository<Delivery> implements Serializable {

    private static final long serialVersionUID = 2038776702918648917L;

    @Override
    public List<Delivery> getAll() {
        return getAll(Delivery.class);
    }

    @Override
    public List<Delivery> getAll(final int start, final int limit) {
        return super.getAll(start, limit, Delivery.class);
    }

    @Override
    public Delivery findById(final Long idToFind) {
        return super.findById(Delivery.class, idToFind);
    }

    /**
     * Adds an order to an existing delivery or creates a new one.
     * <p>
     * Note: changes are not persisted.
     *
     * @param order the order to add
     * @return the created or altered delivery
     */
    public Delivery createDeliveryForOrder(final PizzaOrder order) {

        // find all deliveries in same area
        final Query query = getEm().createNamedQuery(Delivery.FIND_DELIVERY_FOR_STREET);
        query.setParameter(Delivery.PARAM_ADDRESS, order.getAddress().getStreetName());
        List<Delivery> deliveries = query.getResultList();

        // filter out the ones we can't make on time
        Iterator<Delivery> it = deliveries.iterator();
        while (it.hasNext()) {
            if (!withinTimeRange(order, it.next())) {
                it.remove();
            }
        }

        // if we have deliveries left
        if (!deliveries.isEmpty()) {

            // add it to the one with the least amount of orders
            Delivery leastOrders = deliveries.get(0);

            for (Delivery delivery : deliveries) {
                if (delivery.getOrders().size() < leastOrders.getOrders().size()) {
                    leastOrders = delivery;
                }
            }

            leastOrders.getOrders().add(order);

            return leastOrders;
        } else {
            // else create a new delivery
            Delivery newDelivery = new Delivery();

            newDelivery.setStatus(Delivery.Status.ASSIGNED);

            newDelivery.getAddresses().add(order.getAddress());

            Calendar newCalendar = Calendar.getInstance();
            newCalendar.setTime(order.getOrderTime().getTime());
            newCalendar.add(Calendar.MINUTE, BusinessRules.MAX_DELIVERY_WAIT_MIN);
            newDelivery.setDeliverBefore(newCalendar);

            newDelivery.getOrders().add(order);

            return newDelivery;
        }
    }

    // TODO replace with real time information, i.e. driving times from google maps
    private boolean withinTimeRange(final PizzaOrder order, final Delivery next) {
        Calendar processingTime = Calendar.getInstance();
        processingTime.setTime(order.getOrderTime().getTime());
        processingTime.add(Calendar.MINUTE, BusinessRules.BAKE_TIME_MIN);
        processingTime.add(Calendar.MINUTE, BusinessRules.DRIVING_TIME_MIN);
        return processingTime.compareTo(next.getDeliverBefore()) < 0;
    }
}
