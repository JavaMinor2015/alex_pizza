package pizza.domain.concrete.persist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alex on 11/3/15.
 */
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class OrderItem implements Serializable {

    private static final long serialVersionUID = -5850157386425600619L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Pizza pizza;

    private int amount;
}
