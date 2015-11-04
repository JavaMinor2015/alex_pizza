package pizza.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pizza.domain.beans.PizzaRequestBean;
import pizza.domain.concrete.Order;
import pizza.domain.concrete.Pizza;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by alex on 11/4/15.
 */
public class OrderServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    Order order;
    OrderService orderService;
    PizzaRequestBean mockBean;
    List<Pizza> pizzaList;

    @Before
    public void setUp(){
        order = new Order();
        orderService = new OrderService();
        pizzaList = new ArrayList<>();
        pizzaList.add(
                Pizza.builder()
                .id(0)
                .name("Pizza1")
                .price(new BigDecimal(5.10))
                .build()
        );
        pizzaList.add(
                Pizza.builder()
                        .id(1)
                        .name("Pizza2")
                        .price(new BigDecimal(10.53))
                        .build()
        );

        mockBean = mock(PizzaRequestBean.class);
        mockBean.addOrder(order);
        when(mockBean.getAll()).thenReturn(pizzaList);

        orderService.setPizzaBean(mockBean);
    }

    @Test
    public void testInit(){
        orderService.init();
    }

    @Test
    public void testOrderNull() throws Exception {

        doNothing()
                .doThrow(NullPointerException.class)
                .when(mockBean).addOrder(order);
        orderService.setOrder(order);
        orderService.order();
        thrown.expect(NullPointerException.class);
        orderService.order();
    }


}