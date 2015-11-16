package pizza.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pizza.domain.beans.PizzaRequestBean;
import pizza.domain.concrete.persist.OrderItem;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.PizzaOrder;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 11/4/15.
 */
public class PizzaOrderServiceTest {

    /**
     * Rule for exception testing.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    PizzaOrder pizzaOrder;
    OrderService orderService;
    PizzaRequestBean mockBean;
    List<Pizza> pizzaList;

    /**
     * Set it up
     */
    @Before
    public void setUp() {
        pizzaOrder = new PizzaOrder();
        pizzaOrder.add(new OrderItem());
        orderService = new OrderService();
        pizzaList = new ArrayList<>();
        pizzaList.add(
                Pizza.builder()
                        .name("Pizza1")
                        .price((5.10))
                        .build()
        );
        pizzaList.add(
                Pizza.builder()
                        .name("Pizza2")
                        .price((10.53))
                        .build()
        );
        pizzaOrder.getOrderItems().get(0).setAmount(5);
        pizzaOrder.getOrderItems().get(0).setPizza(pizzaList.get(0));
        mockBean = mock(PizzaRequestBean.class);
        mockBean.addOrder(pizzaOrder);
        when(mockBean.getAll()).thenReturn(pizzaList);
        orderService.setPizzaBean(mockBean);
    }

    /**
     * Testing {@link OrderService#init()}
     */
    @Test
    public void testInit() {
        orderService.init();
        assertThat(orderService.getPizzas(), not(nullValue()));
        assertThat(orderService.getPizzaOrder(), not(nullValue()));
    }

    @Test
    public void testInitOrders() {
        orderService.initOrders();
        assertThat(orderService.getOrders(), not(nullValue()));
    }

    /**
     * Testing {@link OrderService#order()}
     *
     * @throws Exception
     */
    @Test
    public void testOrderNull() throws Exception {

        doNothing()
                .doThrow(NullPointerException.class)
                .when(mockBean).addOrder(pizzaOrder);
        orderService.setPizzaOrder(pizzaOrder);
        orderService.order();
        assertThat(orderService.getOrderStatus(), not(nullValue()));
        thrown.expect(NullPointerException.class);
        orderService.order();
    }
}
