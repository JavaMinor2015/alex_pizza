package pizza.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@Builder
public class Topping {
    private String name;
    private Double extraPrice;
}
