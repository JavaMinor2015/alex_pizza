package pizza.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@AllArgsConstructor
public class OrderItem {
    private Pizza pizza;
    private int amount;
}
