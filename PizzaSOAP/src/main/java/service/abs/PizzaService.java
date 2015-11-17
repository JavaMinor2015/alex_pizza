package service.abs;

import java.util.List;
import javax.jws.WebMethod;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.PizzaOrder;

/**
 * Created by alex on 11/17/15.
 */
public interface PizzaService {
    /**
     * Retrieve all pizzas currently in the repository.
     *
     * @return a list of pizzas or an empty list
     */
    @WebMethod
    List<Pizza> getAllPizzas();

    /**
     * Retrieves a list of all the orders currently in the repository.
     *
     * @return a list of orders or an empty list
     */
    @WebMethod
    List<PizzaOrder> getAllOrders();

    /**
     * Retrieve a pizza by its id.
     *
     * @param id the id of the pizza to retrieve
     * @return the corresponding pizza or null
     */
    @WebMethod
    Pizza getPizzaById(final long id);

    /**
     * Order a pizza via a PizzaOrder instance.
     *
     * @param order the PizzaOrder instance.
     */
    @WebMethod
    void orderPizza(final PizzaOrder order);
}
