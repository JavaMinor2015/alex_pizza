package api.controller.abs;

import api.domain.RestDecorator;
import api.domain.abs.HateoasResponse;
import api.error.RestException;
import api.util.RestUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pizza.domain.concrete.persist.abs.PersistentEntity;
import pizza.repository.abs.Repository;

/**
 * Created by alex on 12/4/15.
 * <p/>
 * An abstract repository for REST style controllers.
 *
 * @param <T> a class extending persistent entity
 * @param <R> a repository corresponding to the persistent entity <b>T</b>
 */
@NoArgsConstructor
@Produces({MediaType.APPLICATION_JSON})
public abstract class RestController<
        T extends PersistentEntity,
        R extends Repository<T>> {

    private static final Logger LOGGER = LogManager.getLogger(RestController
            .class);

    // may not be protected or:
    // Illegal non-business method access on no-interface view
    @Setter
    private R repository;

    /**
     * Use this @PostConstruct method to set the repository in the super class.
     */
    @PostConstruct
    public abstract void init();

    /**
     * The url specific to the entity.
     * <p/>
     * i.e. "/pizza" or "/receipt"
     *
     * @return the relative url
     */
    public abstract String getUrl();

    /**
     * Retrieve all items between <b>start</b> and <b>limit</b>.
     *
     * @param start the starting point
     * @param limit the amount of items
     * @return a list of items, empty if none are found.
     */
    @GET
    public Response getAll(
            @DefaultValue("0") @QueryParam("start") final int start,
            @DefaultValue("10") @QueryParam("limit") final int limit
    ) {
        final List<T> items = repository.getAll(start, limit);
        try {
            final HateoasResponse<RestDecorator> response = RestUtil
                    .createHateoas(decorateItems(items), getUrl(), start,
                            limit);
            return RestUtil.buildResponse(response);
        } catch (RestException exception) {
            LOGGER.info(exception.getMessage(), exception);
            return RestUtil.buildError(exception);
        }
    }

    /**
     * Retrieve an item by its id.
     *
     * @param id the id to find the item by.
     * @return the corresponding item or an error message.
     */
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") final long id) {
        final T item = repository.findById(id);
        try {
            final HateoasResponse<RestDecorator> response = RestUtil
                    .createHateoas(decorateItem(item), getUrl(), (int) id, 1);
            return RestUtil.buildResponse(response);
        } catch (RestException exception) {
            LOGGER.info(exception.getMessage(), exception);
            return RestUtil.buildError(exception);
        }
    }

    @POST
    @Path("/create")
    public Response post(final String json) {
        // TODO implement
        // TODO if exists > put
        final HateoasResponse<RestDecorator> response = null;
        return RestUtil.buildResponse(response);
    }

    @PUT
    @Path("/update")
    public Response put(final String json) {
        // TODO implement
        final HateoasResponse<RestDecorator> response = null;
        return RestUtil.buildResponse(response);
    }

    /**
     * Delete an item with the given id.
     *
     * @param id the id of the item to delete
     * @return the deleted item, or a message.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final long id) {
        // TODO implement
        final HateoasResponse<RestDecorator> response = null;
        return RestUtil.buildResponse(response);
    }

    private RestDecorator<T> decorateItem(final T item) {
        return new RestDecorator<>(getUrl() + "/" + item.getId(), item);
    }

    private List<RestDecorator> decorateItems(final List<T> items) {
        List<RestDecorator> itemList = new ArrayList<>(items.size());
        itemList.addAll(items.parallelStream()
                .map(item -> new RestDecorator<>(getUrl() + "/" + item.getId(),
                        item))
                .collect(Collectors.toList()));
        return itemList;
    }
}
