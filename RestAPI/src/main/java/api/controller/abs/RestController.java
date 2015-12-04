package api.controller.abs;

import api.domain.abs.HateoasResponse;
import api.util.RestUtil;
import javax.ws.rs.*;
import javax.xml.ws.Response;
import lombok.AllArgsConstructor;
import pizza.repository.abs.Repository;

/**
 * Created by alex on 12/4/15.
 */
@AllArgsConstructor
public class RestController<T, R extends Repository<T>> {

    final R repository;

    @GET
    @Path("/")
    public Response<T> getAll() {
        final HateoasResponse<T> response = null;
        return new RestUtil<T, HateoasResponse<T>>().buildResponse(response);
    }

    @GET
    @Path("/{id}")
    public Response<T> get(@PathParam("id") final int id) {
        final HateoasResponse<T> response = null;
        return new RestUtil<T, HateoasResponse<T>>().buildResponse(response);
    }

    @POST
    @Path("/create")
    public Response<T> post(final T t) {
        // TODO if exists > put
        final HateoasResponse<T> response = null;
        return new RestUtil<T, HateoasResponse<T>>().buildResponse(response);
    }

    @PUT
    @Path("/update")
    public Response<T> put(final T t) {
        final HateoasResponse<T> response = null;
        return new RestUtil<T, HateoasResponse<T>>().buildResponse(response);
    }

    @DELETE
    @Path("/{id}")
    public Response<T> delete(@PathParam("id") final int id) {
        final HateoasResponse<T> response = null;
        return new RestUtil<T, HateoasResponse<T>>().buildResponse(response);
    }

}
