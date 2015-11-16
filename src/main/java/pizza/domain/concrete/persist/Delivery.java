package pizza.domain.concrete.persist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import pizza.domain.concrete.persist.abs.PersistentEntity;

/**
 * Created by alex on 11/10/15.
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = Delivery.FIND_DELIVERY_FOR_STREET,
        query = "SELECT d FROM Delivery d "
                + "WHERE ("
                + "SELECT a FROM Address a WHERE a.streetName = :" + Delivery.PARAM_ADDRESS
                + ") "
                + "MEMBER OF d.addresses")
public class Delivery extends PersistentEntity implements Serializable {
    public static final String FIND_DELIVERY_FOR_STREET = "findDeliveryForStreet";
    public static final String PARAM_ADDRESS = "address";

    private static final long serialVersionUID = 8909907800574675464L;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<PizzaOrder> orders = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar deliverBefore;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Address> addresses = new ArrayList<>();

    private Status status;

    /**
     * The status of a delivery.
     */
    public enum Status {
        ASSIGNED,
        EN_ROUTE,
        DELIVERED
    }
}
