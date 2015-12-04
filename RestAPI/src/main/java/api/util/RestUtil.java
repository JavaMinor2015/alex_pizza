package api.util;

import api.domain.RestDecorator;
import api.domain.abs.HateoasResponse;
import api.error.RestException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Response;

/**
 * Created by alex on 12/4/15.
 */
public enum RestUtil {;

    public static Response<RestDecorator> buildResponse(
            final HateoasResponse<RestDecorator> item
    ) {
        return null;
    }

    public static HateoasResponse<RestDecorator> createHateoas(
            final RestDecorator item,
            final String url,
            final int start,
            final int limit) {
        if (item == null) {
            throw new RestException("Creation of Hateoas Object failed: no "
                    + "item provided.");
        }
        if (limit < 1 | start < 0) {
            throw new RestException("Creation of Hateoas Object failed: "
                    + "illegal start or limit provided.");
        }
        List<RestDecorator> itemList = new ArrayList<>();
        itemList.add(item);
        return createHateoas(itemList, url, start, limit);
    }

    public static HateoasResponse<RestDecorator> createHateoas(
            final List<RestDecorator> itemList,
            final String url,
            final int start,
            final int limit) {
        if (itemList == null || itemList.isEmpty()) {
            throw new RestException("Creation of response failed: no items "
                    + "provided.");
        }
        if (limit < 1 | start < 0) {
            throw new RestException("Creation of Hateoas Object failed: "
                    + "illegal start or limit provided.");
        }
        return new HateoasResponse<>(
                buildSelfUri(itemList, url, start, limit),
                buildNextUri(itemList, url, start, limit),
                buildPrevUri(itemList, url, start, limit),
                itemList
        );
    }

    private static String buildNextUri(
            final List<RestDecorator> items,
            final String baseUri,
            final int start, final
            int limit) {

        if (limitIsHigherThanItems(items, limit)) {
            // there is no next if we aren't reaching
            // the requested limit of items
            return "";
        }
        return baseUri + "?start=" + (start + limit) + "&limit=" + limit;
    }

    private static String buildPrevUri(
            final List<RestDecorator> items,
            final String baseUri,
            final int start,
            final int limit) {
        if (start == 0) {
            // there is no prev if we are starting at 0
            return "";
        }

        if (start - limit < 0) {
            // if going back takes us below 0
            // start at 0
            return baseUri + "?start=" + 0 + "&limit=" + limit;
        }

        // default
        return baseUri + "?start=" + (start - limit) + "&limit=" + limit;
    }

    private static String buildSelfUri(
            final List<RestDecorator> items,
            final String baseUri,
            final int start,
            final int limit) {
        int lim = limit;
        if (limitIsHigherThanItems(items, limit)) {
            lim = items.size();
        }
        return baseUri + "?start=" + start + "&limit=" + lim;
    }

    private static boolean limitIsHigherThanItems(
            final List<RestDecorator> items,
            final int limit) {
        return (items.size() < limit);
    }
}
