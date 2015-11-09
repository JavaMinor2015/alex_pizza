package pizza.domain.beans;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.PizzaOrder;
import pizza.repository.OrderRepository;
import pizza.repository.PizzaRepository;

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
public class PizzaRequestBeanTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private PizzaRequestBean pizzaRequestBean;
    private PizzaRepository mockPizzaRepo;
    private OrderRepository mockOrderRepo;
    private List<Pizza> pizzaList;
    private List<PizzaOrder> orderList;

    @Before
    public void setUp() {
        mockPizzaRepo = mock(PizzaRepository.class);
        mockOrderRepo = mock(OrderRepository.class);
        pizzaList = new ArrayList<>();
        orderList = new ArrayList<>();

        pizzaList.add(Pizza.builder()
                        .name("Pizza1")
                        .price((5.10))
                        .build()
        );
        orderList.add(new PizzaOrder());
        pizzaRequestBean = new PizzaRequestBean();
        pizzaRequestBean.setOrderRepository(mockOrderRepo);
        pizzaRequestBean.setPizzaRepository(mockPizzaRepo);
    }

    @Test
    public void testAddPizza() throws Exception {
        pizzaRequestBean.addPizza(new Pizza());
        when(mockPizzaRepo.getAll()).thenReturn(pizzaList);
        assertThat(mockPizzaRepo.getAll().get(0).getName(), is("Pizza1"));
    }

    @Test
    public void testAddOrder() throws Exception {
        pizzaRequestBean.addOrder(new PizzaOrder());
        when(mockOrderRepo.getAll()).thenReturn(new ArrayList<>());
        assertThat(pizzaRequestBean.getAll(), not(nullValue()));
    }
}