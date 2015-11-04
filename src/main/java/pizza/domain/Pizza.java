package pizza.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@Builder
public class Pizza implements Serializable {
    private int id;
    private String name;
    private List<Topping> toppings;

    @Getter(AccessLevel.NONE)
    private BigDecimal price;

    public BigDecimal getPrice() {
        BigDecimal total = price;

        for (Topping topping : toppings) {
            total = total.add(topping.getExtraPrice());
        }

        return total.setScale(2, BigDecimal.ROUND_UP);
    }
}
