package Pizza.rest;

import Pizza.model.PizzaJson;
import com.google.gson.*;
import java.lang.reflect.Type;

/**
 * Created by alex on 12/14/15.
 */
public class PizzaAdapter implements JsonDeserializer<PizzaJson> {
    @Override
    public PizzaJson deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        PizzaJson pizzaJson = new PizzaJson();
        JsonObject json = jsonElement.getAsJsonObject();
        JsonArray items = json.getAsJsonArray("items");

        for (JsonElement item : items) {
            JsonObject obj = item.getAsJsonObject();
            JsonObject i = obj.getAsJsonObject("item");
            String name = i.get("name").getAsString();
            pizzaJson.setName(name);
            break;
        }
        return pizzaJson;
    }
}
