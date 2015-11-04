package pizza.domain.beans;

import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Stateful
public class PizzaRequestBean {

    @Inject
    PizzaRepository repository;

    public void add(final Pizza pizza) {
        repository.add(pizza);
    }

    public List<Pizza> getAll(){
        return repository.getAll();
    }
}
