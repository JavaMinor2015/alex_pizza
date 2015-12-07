package api.controller;

import api.domain.RestDecorator;
import api.domain.abs.HateoasResponse;
import api.util.RestUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import pizza.domain.concrete.persist.Pizza;
import pizza.repository.PizzaRepository;

/**
 * Generated 4-12-2015.
 *
 * @author Alex
 */
@Path(PizzaRestController.URL)
public class PizzaRestController {

    public static final String URL = "/pizza";

    @EJB
    private PizzaRepository repository;

    public PizzaRestController(){
        System.out.println("OMG JUST WORK ALREADY");
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
        //setRepository(repository);
//        setRepository(null);
    }

    public String getUrl() {
        return URL;
    }

    @GET
    @Produces("application/json")
    public Response getAll(
            @DefaultValue("0") @QueryParam("start") final int start,
            @DefaultValue("10") @QueryParam("limit") final int limit
    ) {
        final List<Pizza> items = repository.getAll();
        final HateoasResponse<RestDecorator> response = RestUtil
                .createHateoas(decorateItems(items), getUrl(), start, limit);
        return RestUtil.buildResponse(response);
    }

    private RestDecorator<Pizza> decorateItem(final Pizza item) {
        return new RestDecorator<>(getUrl() + "/" + item.getId(), item);
    }

    private List<RestDecorator> decorateItems(final List<Pizza> items) {
        List<RestDecorator> itemList = new ArrayList<>(items.size());
        itemList.addAll(items.parallelStream()
                .map(item -> new RestDecorator<>(getUrl() + "/" + item.getId(),
                        item))
                .collect(Collectors.toList()));
        return itemList;
    }

}
