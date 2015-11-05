package pizza.domain.concrete.persist;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
public class Pizza implements Serializable {
    private static final long serialVersionUID = -2394835763685701096L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
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
