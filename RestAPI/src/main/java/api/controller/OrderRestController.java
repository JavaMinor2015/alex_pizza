package api.controller;

import api.controller.abs.RestController;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import pizza.domain.concrete.persist.PizzaOrder;
import pizza.repository.OrderRepository;

/**
 * Generated 7-12-2015.
 *
 * @author Alex
 */
@Path(OrderRestController.URL)
public class OrderRestController extends RestController<PizzaOrder,
        OrderRepository> {

    public static final String URL = "/orders";

    @EJB
    private OrderRepository repository;

    @Override
    @PostConstruct
    public void init() {
        setRepository(repository);
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
