package pizza.domain.concrete.persist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@Builder
@Entity
public class Topping implements Serializable {
    private static final long serialVersionUID = -4925807675931819331L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private BigDecimal extraPrice;
}
