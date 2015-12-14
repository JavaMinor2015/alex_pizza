package Pizza.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by alex on 12/14/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PizzaJson {
    private String name;
}
