package pizza.domain.concrete.persist;

import lombok.*;
import pizza.domain.concrete.persist.abs.PersistentEntity;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Topping extends PersistentEntity implements Serializable {
    private static final long serialVersionUID = -4925807675931819331L;

    private String name;

    private double extraPrice;
}
