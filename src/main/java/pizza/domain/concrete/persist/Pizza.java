package pizza.domain.concrete.persist;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pizza implements Serializable {
    private static final long serialVersionUID = -2394835763685701096L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
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
