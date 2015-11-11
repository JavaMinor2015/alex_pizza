package pizza.domain.concrete.persist;

import lombok.*;
import pizza.domain.concrete.persist.abs.PersistentEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
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
public class Pizza extends PersistentEntity implements Serializable {
    private static final long serialVersionUID = -2394835763685701096L;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
