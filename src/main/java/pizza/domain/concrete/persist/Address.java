package pizza.domain.concrete.persist;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.builder.HashCodeBuilder;
import pizza.domain.concrete.persist.abs.PersistentEntity;

/**
 * Created by alex on 11/10/15.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends PersistentEntity implements Serializable {
    private static final long serialVersionUID = 2987205388891445593L;

    private String streetName;
    private static final int PRIME_1 = 17;
    private static final int PRIME_2 = 31;

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        return getStreetName().equals(other.getStreetName());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(PRIME_1, PRIME_2)
                .append(streetName)
                .toHashCode();
    }
}
