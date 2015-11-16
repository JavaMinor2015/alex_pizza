package pizza.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.Before;
import org.junit.Test;
import pizza.domain.concrete.persist.Address;
import pizza.domain.concrete.persist.Delivery;
import pizza.domain.concrete.persist.PizzaOrder;
import pizza.rules.BusinessRules;

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
    private CriteriaBuilder mockBuilder;
    private CriteriaQuery<Delivery> mockCriteriaQuery;
    private TypedQuery mockTypedQuery;
    private Root<Delivery> mockRoot;

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
        withinRange.add(Calendar.MINUTE, BusinessRules.MAX_DELIVERY_WAIT_MIN - 10);
        Calendar outsideRange = Calendar.getInstance();
        outsideRange.add(Calendar.MINUTE,
                BusinessRules.MAX_DELIVERY_WAIT_MIN
                        - BusinessRules.BAKE_TIME_MIN
                        - BusinessRules.DRIVING_TIME_MIN
                        - 100
        );

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
        deliveryList.add(existingDeliveryWithinTimeRange2);
        deliveryList.add(existingDeliveryOutsideTimeRange);


        mockManager = mock(EntityManager.class);
        mockQuery = mock(Query.class);
        mockBuilder = mock(CriteriaBuilder.class);
        mockCriteriaQuery = mock(CriteriaQuery.class);
        mockTypedQuery = mock(TypedQuery.class);
        deliveryRepository = new DeliveryRepository();
        deliveryRepository.setEm(mockManager);

        when(mockManager.getCriteriaBuilder()).thenReturn(mockBuilder);
        when(mockBuilder.createQuery(Delivery.class)).thenReturn(mockCriteriaQuery);
        when(mockCriteriaQuery.from(Delivery.class)).thenReturn(mockRoot);
        when(mockManager.createQuery(mockCriteriaQuery)).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getResultList()).thenReturn(deliveryList);
    }

    @Test
    public void testCreateDeliveryForOrder() throws Exception {
        when(mockQuery.getResultList()).thenReturn(deliveryList);
        when(mockManager.createNamedQuery(Delivery.FIND_DELIVERY_FOR_STREET)).thenReturn(mockQuery);

        // make sure the three deliveries are in the repo
        assertThat(deliveryRepository.getAll().size(), is(3));

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