package pizza.repository;

import org.junit.Before;
import org.junit.Test;
import pizza.domain.concrete.persist.OrderItem;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.PizzaOrder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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
public class OrderRepositoryTest {

    private EntityManager mockManager;
    private Query mockQuery;
    private List<PizzaOrder> pizzaOrders;
    private OrderRepository repository;

    @Before
    public void setUp() {
        mockManager = mock(EntityManager.class);
        mockQuery = mock(Query.class);
        pizzaOrders = new ArrayList<>();
        repository = new OrderRepository();
        repository.setEm(mockManager);
        when(mockManager.createQuery(any(String.class))).thenReturn(mockQuery);
        PizzaOrder order = new PizzaOrder();
        order.setId(123L);
        order.add(new OrderItem(456L, new Pizza(789L, "Pizza", null, 5D), 2));
        pizzaOrders.add(order);
    }

    @Test
    public void testGetPizzaOrders() throws Exception {
        when(mockQuery.getResultList()).thenReturn(pizzaOrders);
        assertThat(repository.getPizzaOrders(), not(nullValue()));
        assertThat(repository.getPizzaOrders(), is(pizzaOrders));
    }

    @Test
    public void testAddItem() throws Exception {
        PizzaOrder order = new PizzaOrder();
        order.setId(987L);
        repository.addItem(order);

        pizzaOrders.add(order);
        when(mockQuery.getResultList()).thenReturn(pizzaOrders);
        assertThat(repository.getPizzaOrders().contains(order), is(true));
    }

    @Test
    public void testLoad() throws Exception {
        when(mockQuery.getResultList()).thenReturn(pizzaOrders);
        repository.load();
    }

    @Test
    public void testSave() throws Exception {
        repository.save();
    }
}