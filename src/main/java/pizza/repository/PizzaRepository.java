package pizza.repository;

import pizza.domain.Pizza;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Stateful
public class PizzaRepository {
    private List<Pizza> pizzaList = new ArrayList<>();

    public void add(final Pizza pizza) {
        pizzaList.add(pizza);
    }

    public List<Pizza> getAll() {
        return pizzaList;
    }
}
