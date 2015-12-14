package api.util;

import api.domain.RestDecorator;
import api.domain.abs.HateoasResponse;
import api.error.RestException;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import pizza.rules.Globals;

/**
 * Created by alex on 12/4/15.
 */
public enum RestUtil {
    ;
    private static final GsonBuilder GSON = new GsonBuilder();

    public static Response buildResponse(
            final HateoasResponse<RestDecorator> item
    ) {
        //TODO refine
        String r = GSON.create().toJson(item, HateoasResponse.class);
        return Response.ok(r).build();
    }

    public static Response buildError(final RestException exception) {
        // TODO expand?
        return Response.noContent().build();
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
        // TODO will never occur because of earlier exception on repository
        // level
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
        // TODO will never occur because of earlier exception on repository
        // level
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
        return baseUri + "?" + Globals.PAGE_DELIMITER + "=" + (start + limit)
                + "&" + Globals.PER_PAGE_DELIMITER + "=" + limit;
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
            return baseUri + "?" + Globals.PAGE_DELIMITER + "=" + 0 + "&" +
                    Globals.PER_PAGE_DELIMITER + "=" + limit;
        }

        // default
        return baseUri + "?" + Globals.PAGE_DELIMITER + "=" + (start - limit)
                + "&" + Globals.PER_PAGE_DELIMITER + "=" + limit;
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
        return baseUri + "?" + Globals.PAGE_DELIMITER + "=" + start + "&" +
                Globals.PER_PAGE_DELIMITER + "=" + lim;
    }

    private static boolean limitIsHigherThanItems(
            final List<RestDecorator> items,
            final int limit) {
        return (items.size() < limit);
    }
}