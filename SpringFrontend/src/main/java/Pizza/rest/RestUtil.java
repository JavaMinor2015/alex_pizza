package Pizza.rest;


import Pizza.adapters.AbstractDeserializer;
import com.google.gson.GsonBuilder;

/**
 * Created by alex on 12/4/15.
 */
public enum RestUtil {
    ;

    public static GsonBuilder getBuilder(final AbstractDeserializer... deserializers) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        for (AbstractDeserializer deserializer : deserializers) {
            gsonBuilder.registerTypeAdapter(deserializer.getClazz(),deserializer);
        }

        return gsonBuilder;
    }
}

