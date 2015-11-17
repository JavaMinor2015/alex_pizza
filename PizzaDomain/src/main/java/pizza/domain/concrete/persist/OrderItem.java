package pizza.domain.concrete.persist;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pizza.domain.concrete.persist.abs.PersistentEntity;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends PersistentEntity implements Serializable {

    private static final long serialVersionUID = -5850157386425600619L;

    @OneToOne(cascade = CascadeType.MERGE)
    private Pizza pizza;

    private int amount;
}
