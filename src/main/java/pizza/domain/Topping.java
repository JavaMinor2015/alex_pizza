package pizza.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@Builder
public class Topping implements Serializable {
    private String name;
    private Double extraPrice;
}
