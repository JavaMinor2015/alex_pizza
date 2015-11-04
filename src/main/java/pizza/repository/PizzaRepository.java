package pizza.repository;

import pizza.domain.Pizza;
import pizza.domain.Topping;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Stateful
public class PizzaRepository implements Serializable {
    private List<Pizza> pizzaList = new ArrayList<>();

    public void add(final Pizza pizza) {
        pizzaList.add(pizza);
    }

    public List<Pizza> getAll() {
        return pizzaList;
    }

    public void load() {
        // magical persistence here
        pizzaList.add(
                Pizza.builder()
                        .id(0)
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
                        .build()
        );
        pizzaList.add(
                Pizza.builder()
                        .id(1)
                        .name("Hawaii")
                        .toppings(
                                Arrays.asList(
                                        Topping.builder()
                                                .name("Pineapple")
                                                .extraPrice(2.0)
                                                .build()
                                        ,
                                        Topping.builder()
                                                .name("Ham")
                                                .extraPrice(0.33)
                                                .build())
                        )
                        .price(10.0)
                        .build()
        );
    }

    public void save() {
        // magical persistence here
    }
}
