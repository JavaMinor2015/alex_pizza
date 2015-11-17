package service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import pizza.domain.beans.PizzaRequestBean;
import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.PizzaOrder;
import service.abs.PizzaService;

/**
 * Created by alex on 11/16/15.
 */
@Stateless
@WebService
public class PizzaServiceBean implements PizzaService {

    @EJB
    private PizzaRequestBean bean;

    @Override
    public List<Pizza> getAllPizzas() {
        return bean.getAll();
    }

    @Override
    public Pizza getPizzaById(final long id) {
        return bean.findById(id);
    }

    @Override
    public void orderPizza(final PizzaOrder order) {
        bean.addOrder(order);
    }


}
