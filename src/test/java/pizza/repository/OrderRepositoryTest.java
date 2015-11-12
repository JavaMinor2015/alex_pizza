package pizza.repository;

import org.junit.Before;
import org.junit.Test;
import pizza.domain.concrete.persist.OrderItem;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.PizzaOrder;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by alex on 11/9/15.
 */
public class OrderRepositoryTest {

    private TypedQuery<PizzaOrder> mockTypedQuery;
    private Root<PizzaOrder> mockRoot;
    private List<PizzaOrder> pizzaOrders;
    private OrderRepository repository;

    @Before
    public void setUp() {
        EntityManager mockManager = mock(EntityManager.class);
        CriteriaQuery<PizzaOrder> mockCriteriaQuery = mock(CriteriaQuery.class);
        mockTypedQuery = mock(TypedQuery.class);
        mockRoot = mock(Root.class);
        CriteriaBuilder mockCriteriaBuilder = mock(CriteriaBuilder.class);
        pizzaOrders = new ArrayList<>();
        repository = new OrderRepository();
        repository.setEm(mockManager);
        PizzaOrder order = new PizzaOrder();
        order.add(new OrderItem(new Pizza("Pizza", 1, 1, null, 5D), 2));
        order.setId(123L);
        pizzaOrders.add(order);

        when(mockCriteriaBuilder.createQuery(PizzaOrder.class)).thenReturn(mockCriteriaQuery);
        when(mockManager.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
        when(mockManager.createQuery(mockCriteriaQuery)).thenReturn(mockTypedQuery);
        when(mockCriteriaQuery.from(PizzaOrder.class)).thenReturn(mockRoot);
    }

    @Test
    public void testGetPizzaOrders() throws Exception {
        when(mockTypedQuery.getResultList()).thenReturn(pizzaOrders);
        assertThat(repository.getAll(), not(nullValue()));
        assertThat(repository.getAll(), is(pizzaOrders));
    }

    @Test
    public void testAddItem() throws Exception {
        PizzaOrder order = new PizzaOrder();
        repository.add(order);

        pizzaOrders.add(order);

        when(mockTypedQuery.getResultList()).thenReturn(pizzaOrders);
        assertThat(repository.getAll().contains(order), is(true));
    }

    @Test
    public void testSave() throws Exception {
        repository.save();
    }

    @Test
    public void testFindById() throws Exception {
        when(mockTypedQuery.getSingleResult()).thenReturn(pizzaOrders.get(0));
        assertThat(repository.findById(123L), is(pizzaOrders.get(0)));
    }
}