package pizza.domain.concrete.persist;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import lombok.*;
import pizza.domain.concrete.persist.abs.PersistentEntity;

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

    private long amountSoldTotal;
    private long amountSold;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ingredient> ingredients;

    @Getter(AccessLevel.NONE)
    private double price;

    public double getPrice() {
        double total = price;

        for (Ingredient ingredient : ingredients) {
            total += ingredient.getExtraPrice();
        }

        return total;
    }
}
