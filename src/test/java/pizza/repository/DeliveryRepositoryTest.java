package pizza.repository;

import org.junit.Before;
import org.junit.Test;
import pizza.domain.concrete.persist.Address;
import pizza.domain.concrete.persist.Delivery;
import pizza.domain.concrete.persist.PizzaOrder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by alex on 11/10/15.
 */
public class DeliveryRepositoryTest {

    private EntityManager mockManager;
    private Query mockQuery;
    private Delivery existingDeliveryWithinTimeRange;
    private Delivery existingDeliveryWithinTimeRange2;
    private Delivery existingDeliveryOutsideTimeRange;
    private PizzaOrder existingOrder1;
    private PizzaOrder existingOrder2;
    private List<Delivery> deliveryList;
    private PizzaOrder testOrder;
    private Address testAddress;

    private DeliveryRepository deliveryRepository;

    @Before
    public void setUp() {

        // test data and existing items
        testAddress = new Address();
        testAddress.setStreetName("testStreet");

        testOrder = new PizzaOrder();
        testOrder.setOrderTime(Calendar.getInstance());
        testOrder.setAddress(testAddress);

        existingOrder1 = new PizzaOrder();
        existingOrder2 = new PizzaOrder();


        Calendar withinRange = Calendar.getInstance();
        withinRange.setTime(testOrder.getOrderTime().getTime());
        withinRange.add(Calendar.MINUTE, 50);
        Calendar outsideRange = Calendar.getInstance();
        outsideRange.add(Calendar.MINUTE, -40);

        existingDeliveryWithinTimeRange = Delivery.builder()
                .status(Delivery.Status.ASSIGNED)
                .deliverBefore(withinRange)
                .addresses(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
        existingDeliveryWithinTimeRange.getAddresses().add(testAddress);
        existingDeliveryWithinTimeRange.getOrders().add(existingOrder1);


        existingDeliveryWithinTimeRange2 = Delivery.builder()
                .status(Delivery.Status.ASSIGNED)
                .deliverBefore(withinRange)
                .addresses(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
        existingDeliveryWithinTimeRange2.getAddresses().add(testAddress);
        existingDeliveryWithinTimeRange2.getOrders().add(existingOrder1);
        existingDeliveryWithinTimeRange2.getOrders().add(existingOrder2);

        existingDeliveryOutsideTimeRange = Delivery.builder()
                .status(Delivery.Status.ASSIGNED)
                .deliverBefore(outsideRange)
                .addresses(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
        existingDeliveryOutsideTimeRange.getAddresses().add(testAddress);

        deliveryList = new ArrayList<>();
        deliveryList.add(existingDeliveryWithinTimeRange);
        deliveryList.add(existingDeliveryOutsideTimeRange);


        mockManager = mock(EntityManager.class);
        mockQuery = mock(Query.class);
        deliveryRepository = new DeliveryRepository();
        deliveryRepository.setEm(mockManager);
    }

    @Test
    public void testCreateDeliveryForOrder() throws Exception {
        when(mockQuery.getResultList()).thenReturn(deliveryList);
        when(mockManager.createNamedQuery(Delivery.FIND_DELIVERY_FOR_STREET)).thenReturn(mockQuery);

        // test with an order that should end up in existingDeliveryWithinTimeRange
        // since it's one of two within time range and has the least amount of
        // orders
        Delivery result = deliveryRepository.createDeliveryForOrder(testOrder);
        assertThat(result, is(existingDeliveryWithinTimeRange));

        // test with an order that should end up in a new delivery because its
        // address differs
        when(mockQuery.getResultList()).thenReturn(new ArrayList());
        testOrder.setAddress(new Address("otherStreet"));
        result = deliveryRepository.createDeliveryForOrder(testOrder);
        assertThat(result, is(not(existingDeliveryWithinTimeRange)));
        assertThat(result, is(not(existingDeliveryWithinTimeRange2)));
        assertThat(result, is(not(existingDeliveryOutsideTimeRange)));

    }
}