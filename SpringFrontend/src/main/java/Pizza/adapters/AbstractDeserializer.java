package Pizza.adapters;

import com.google.gson.JsonDeserializer;

/**
 * Created by alex on 12/14/15.
 */
public abstract class  AbstractDeserializer<T> implements JsonDeserializer<T> {
    public abstract Class<T> getClazz();
}
