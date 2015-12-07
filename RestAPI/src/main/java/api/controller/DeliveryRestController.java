package api.controller;

import api.controller.abs.RestController;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import pizza.domain.concrete.persist.Delivery;
import pizza.repository.DeliveryRepository;

/**
 * Generated 7-12-2015.
 *
 * @author Alex
 */
@Path(DeliveryRestController.URL)
public class DeliveryRestController extends RestController<Delivery, DeliveryRepository> {

    public static final String URL = "/deliveries";

    @EJB
    private DeliveryRepository repository;

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
