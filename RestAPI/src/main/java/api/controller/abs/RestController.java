package api.controller.abs;

import api.domain.RestDecorator;
import api.domain.abs.HateoasResponse;
import api.util.RestUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    // may not be protected or:
    // Illegal non-business method access on no-interface view
    @Setter
    private R repository;

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
        final List<T> items = repository.getAll();
        final HateoasResponse<RestDecorator> response = RestUtil
                .createHateoas(decorateItems(items), getUrl(), start, limit);
        return RestUtil.buildResponse(response);
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
        final HateoasResponse<RestDecorator> response = RestUtil
                .createHateoas(decorateItem(item), getUrl(), (int) id, 1);
        return RestUtil.buildResponse(response);
    }

    @POST
    @Path("/create")
    public Response post(final RestDecorator t) {
        // TODO implement
        // TODO if exists > put
        final HateoasResponse<RestDecorator> response = null;
        return RestUtil.buildResponse(response);
    }

    @PUT
    @Path("/update")
    public Response put(final RestDecorator t) {
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
