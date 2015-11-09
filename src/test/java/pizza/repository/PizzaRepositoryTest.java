package pizza.repository;

import org.junit.Before;
import org.junit.Test;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.Topping;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by alex on 11/9/15.
 */
public class PizzaRepositoryTest {

    private PizzaRepository repository;
    private EntityManager mockManager;
    private Query mockQuery;
    private List<Pizza> pizzas;

    @Before
    public void setUp() {
        repository = new PizzaRepository();
        mockManager = mock(EntityManager.class);
        mockQuery = mock(Query.class);
        pizzas = new ArrayList<>();
        repository.setEm(mockManager);
        when(mockManager.createQuery(any(String.class))).thenReturn(mockQuery);
        Pizza pizza = Pizza.builder()
                .id(1L)
                .name("Margherita")
                .toppings(
                        Arrays.asList(
                                Topping.builder()
                                        .name("Peperoni")
                                        .extraPrice(1.0)
                                        .build()
                                ,
                                Topping.builder()
                                        .name("Onions")
                                        .extraPrice(0.5)
                                        .build())
                )
                .price(10.0)
                .build();
        pizzas.add(pizza);
    }

    @Test
    public void testAdd() throws Exception {
        Pizza p = pizzas.get(0);
        repository.add(p);
        when(mockQuery.getResultList()).thenReturn(pizzas);
        assertThat(repository.getAll().contains(p), is(true));
    }

    @Test
    public void testLoad() throws Exception {
        when(mockQuery.getResultList()).thenReturn(pizzas);
        repository.load();
    }

    @Test
    public void testSave() throws Exception {
        repository.save();
    }
}