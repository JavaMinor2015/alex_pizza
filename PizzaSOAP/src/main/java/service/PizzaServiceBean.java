package service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import pizza.domain.concrete.persist.Pizza;
import pizza.repository.PizzaRepository;

/**
 * Created by alex on 11/16/15.
 */
@Stateless
@WebService
public class PizzaServiceBean {

    @EJB
    private PizzaRepository pizzaRepository;

    @WebMethod
    public List<Pizza> getPizzas() {
        return pizzaRepository.getAll();
    }
}
