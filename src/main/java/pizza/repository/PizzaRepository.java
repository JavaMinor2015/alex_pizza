package pizza.repository;

import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.Topping;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Stateful
public class PizzaRepository implements Serializable {
    private List<Pizza> pizzaList = new ArrayList<>();

    /**
     * Add a pizza to this repository.
     *
     * @param pizza the pizza to add
     */
    public void add(final Pizza pizza) {
        pizzaList.add(pizza);
    }

    /**
     * Retrieve all pizzas.
     * <p>
     * {@link #load()} not called
     *
     * @return a list of pizzas
     */
    public List<Pizza> getAll() {
        return pizzaList;
    }

    /**
     * Load state from persistence.
     */
    public void load() {
        // magical persistence here
        pizzaList.add(
                Pizza.builder()
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
                                                .extraPrice((0.5))
                                                .build())
                        )
                        .price((10.0))
                        .build()
        );
        pizzaList.add(
                Pizza.builder()
                        .id(2L)
                        .name("Hawaii")
                        .toppings(
                                Arrays.asList(
                                        Topping.builder()
                                                .name("Pineapple")
                                                .extraPrice((2.0))
                                                .build()
                                        ,
                                        Topping.builder()
                                                .name("Ham")
                                                .extraPrice((0.33))
                                                .build())
                        )
                        .price((10.0))
                        .build()
        );
    }

    /**
     * Save state to persistence.
     */
    public void save() {
        // magical persistence here
    }
}
