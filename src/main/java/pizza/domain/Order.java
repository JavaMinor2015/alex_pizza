package pizza.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
public class Order {
    private List<Pizza> pizzaList;
    private List<Integer> amounts;

}
