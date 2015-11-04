package pizza.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@AllArgsConstructor
public class OrderItem implements Serializable{
    private Pizza pizza;
    private int amount;
}
