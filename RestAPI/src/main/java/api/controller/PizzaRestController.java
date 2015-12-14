package api.controller;

import api.controller.abs.RestController;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import pizza.domain.concrete.persist.Pizza;
import pizza.repository.PizzaRepository;

/**
 * Generated 4-12-2015.
 *
 * @author Alex
 */
@Path(PizzaRestController.URL)
public class PizzaRestController extends RestController<Pizza,
        PizzaRepository> {

    public static final String URL = "/pizzas";

    @EJB
    private PizzaRepository repository;

    @Override
    @PostConstruct
    public void init() {
        setRepository(repository);
    }

    public String getUrl() {
        return URL;
    }

}
