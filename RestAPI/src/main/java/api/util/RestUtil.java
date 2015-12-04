package api.util;

import api.domain.abs.HateoasResponse;
import javax.xml.ws.Response;

/**
 * Created by alex on 12/4/15.
 */
public class RestUtil<T, H extends HateoasResponse> {

    public Response<T> buildResponse(final H item) {
        return null;
    }

    public HateoasResponse<T> createHateoas(final T item) {
        return null;
    }
}
