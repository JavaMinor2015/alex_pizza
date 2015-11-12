package pizza.domain.concrete.persist;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 11/9/15.
 */
public class PizzaTest {

    @Test
    public void testGetPrice() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setName("Pizza2");
        pizza.setPrice(5D);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(
                new Ingredient("Topping1", 5, Ingredient.MeasurementType.GRAM, 5D)
        );
        pizza.setIngredients(
                ingredients
        );

        assertThat(pizza.getPrice(), is(10D));
    }
}