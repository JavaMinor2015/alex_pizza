package api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pizza.domain.concrete.persist.abs.PersistentEntity;

/**
 * Generated 4-12-2015.
 *
 * @author Alex
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RestDecorator<T extends PersistentEntity> {
    private String self;
    private T item;
}
