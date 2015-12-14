package Pizza.rest;


import Pizza.model.PizzaJson;
import com.google.gson.GsonBuilder;

/**
 * Created by alex on 12/4/15.
 */
public enum RestUtil {
    ;

    public static PizzaJson createFromJson(final String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PizzaJson.class, new PizzaAdapter());
        return gsonBuilder.create().fromJson(json, PizzaJson.class);
    }
}

