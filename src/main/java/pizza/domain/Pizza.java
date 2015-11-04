package pizza.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@Builder
public class Pizza {
    private int id;
    private String name;
    private List<Topping> toppings;

    @Getter(AccessLevel.NONE)
    private double price;

    public double getPrice() {
        double total = price;

        for (Topping topping : toppings) {
            total += topping.getExtraPrice();
        }
        return total;
    }
}
