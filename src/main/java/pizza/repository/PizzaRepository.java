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
                        .id(0L)
                        .name("Margherita")
                        .toppings(
                                Arrays.asList(
                                        Topping.builder()
                                                .name("Peperoni")
                                                .extraPrice(new BigDecimal(1.0))
                                                .build()
                                        ,
                                        Topping.builder()
                                                .name("Onions")
                                                .extraPrice(new BigDecimal(0.5))
                                                .build())
                        )
                        .price(new BigDecimal(10.0))
                        .build()
        );
        pizzaList.add(
                Pizza.builder()
                        .id(1L)
                        .name("Hawaii")
                        .toppings(
                                Arrays.asList(
                                        Topping.builder()
                                                .name("Pineapple")
                                                .extraPrice(new BigDecimal(2.0))
                                                .build()
                                        ,
                                        Topping.builder()
                                                .name("Ham")
                                                .extraPrice(new BigDecimal(0.33))
                                                .build())
                        )
                        .price(new BigDecimal(10.0))
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
