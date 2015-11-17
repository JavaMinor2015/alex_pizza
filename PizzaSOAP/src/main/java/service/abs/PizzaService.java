package service.abs;

import java.util.List;
import javax.jws.WebMethod;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.PizzaOrder;

/**
 * Created by alex on 11/17/15.
 */
public interface PizzaService {
    @WebMethod
    List<Pizza> getAllPizzas();

    @WebMethod
    Pizza getPizzaById(final long id);

    @WebMethod
    void orderPizza(final PizzaOrder order);
}
