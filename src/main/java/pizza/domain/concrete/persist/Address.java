package pizza.domain.concrete.persist;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.HashCodeBuilder;
import pizza.domain.concrete.persist.abs.PersistentEntity;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by alex on 11/10/15.
 */
@Entity
@Getter
@Setter
public class Address extends PersistentEntity implements Serializable {
    private static final long serialVersionUID = 2987205388891445593L;

    private String streetName;

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
        return new HashCodeBuilder(17, 31)
                .append(streetName)
                .toHashCode();
    }
}
