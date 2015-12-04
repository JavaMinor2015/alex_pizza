package api.controller;

import api.controller.abs.RestController;
import javax.annotation.PostConstruct;
import javax.ws.rs.Path;
import pizza.domain.concrete.persist.Pizza;
import pizza.repository.PizzaRepository;

/**
 * Generated 4-12-2015.
 *
 * @author Alex
 */
@Path(PizzaRestController.URL)
public class PizzaRestController extends RestController<Pizza, PizzaRepository> {

    public static final String URL = "/pizza";

//    @EJB
//    private PizzaRepository repository;

    public PizzaRestController(){
        System.out.println("OMG JUST WORK ALREADY");
    }

    @PostConstruct
    public void init() {
//        setRepository(repository);
        setRepository(null);
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
