package pizza.domain.concrete.persist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 11/9/15.
 */
public class PizzaTest {

    @Test
    public void testGetPrice() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setId(5L);
        pizza.setName("Pizza2");
        pizza.setPrice(5D);
        List<Topping> toppings = new ArrayList<>();
        toppings.add(
                new Topping(1L, "Topping1", 5D)
        );
        pizza.setToppings(
                toppings
        );

        assertThat(pizza.getPrice(), is(10D));
    }
}