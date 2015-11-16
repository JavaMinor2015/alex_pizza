package pizza.domain.concrete.persist;

import java.io.Serializable;
import javax.persistence.Entity;
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
public class Ingredient extends PersistentEntity implements Serializable {
    private static final long serialVersionUID = -4925807675931819331L;

    private String name;
    private double amount;
    private MeasurementType measurementType;
    private double extraPrice;

    /**
     * The type of measurement used.
     */
    public enum MeasurementType {
        KILOGRAM,
        GRAM,
        LITER,
        MILLILITER;
    }
}
