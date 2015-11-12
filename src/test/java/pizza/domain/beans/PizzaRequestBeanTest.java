package pizza.domain.beans;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pizza.domain.concrete.persist.Delivery;
import pizza.domain.concrete.persist.OrderItem;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.PizzaOrder;
import pizza.repository.DeliveryRepository;
import pizza.repository.OrderRepository;
import pizza.repository.PizzaRepository;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by alex on 11/9/15.
 */
public class PizzaRequestBeanTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private PizzaRequestBean pizzaRequestBean;
    private PizzaRepository mockPizzaRepo;
    private OrderRepository mockOrderRepo;
    private DeliveryRepository mockDeliveryRepo;
    private List<Pizza> pizzaList;
    private List<PizzaOrder> orderList;
    private List<Delivery> deliveryList;

    @Before
    public void setUp() {
        mockPizzaRepo = mock(PizzaRepository.class);
        mockOrderRepo = mock(OrderRepository.class);
        mockDeliveryRepo = mock(DeliveryRepository.class);
        pizzaList = new ArrayList<>();
        deliveryList = new ArrayList<>();
        orderList = new ArrayList<>();

        deliveryList.add(new Delivery());
        pizzaList.add(Pizza.builder()
                        .name("Pizza1")
                        .price((5.10))
                        .build()
        );
        orderList.add(new PizzaOrder());
        orderList.get(0).setOrderItems(new ArrayList<>());
        orderList.get(0).getOrderItems().add(new OrderItem());
        orderList.get(0).getOrderItems().get(0).setAmount(0);
        orderList.get(0).getOrderItems().get(0).setPizza(pizzaList.get(0));
        pizzaRequestBean = new PizzaRequestBean();
        pizzaRequestBean.setOrderRepository(mockOrderRepo);
        pizzaRequestBean.setPizzaRepository(mockPizzaRepo);
        pizzaRequestBean.setDeliveryRepository(mockDeliveryRepo);
    }

    @Test
    public void testAddPizza() throws Exception {
        pizzaRequestBean.addPizza(new Pizza());
        when(mockPizzaRepo.getAll()).thenReturn(pizzaList);
        assertThat(mockPizzaRepo.getAll().get(0).getName(), is("Pizza1"));
    }

    @Test
    public void testAddOrder() throws Exception {
        when(mockDeliveryRepo.createDeliveryForOrder(any(PizzaOrder.class))).thenReturn(new Delivery());
        pizzaRequestBean.addOrder(orderList.get(0));
        when(mockOrderRepo.getAll()).thenReturn(new ArrayList<>());
        assertThat(pizzaRequestBean.getAll(), not(nullValue()));
    }

    @Test
    public void testGetOrders() throws Exception {
        when(mockOrderRepo.getAll()).thenReturn(orderList);
        assertThat(pizzaRequestBean.getOrders(), is(orderList));
    }

    @Test
    public void testGetDeliveries() throws Exception {
        when(mockDeliveryRepo.getAll()).thenReturn(deliveryList);
        assertThat(pizzaRequestBean.getDeliveries(), is(deliveryList));
    }

    @Test
    public void testFindById() throws Exception {
        when(mockPizzaRepo.findById(5L)).thenReturn(pizzaList.get(0));
        assertThat(pizzaRequestBean.findById(5L), is(pizzaList.get(0)));
    }
}