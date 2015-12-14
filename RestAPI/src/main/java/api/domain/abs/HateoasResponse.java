package api.domain.abs;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 12/4/15.
 */
@Getter
@Setter
@AllArgsConstructor
public class HateoasResponse<T> {
    private final String self;
    private final String next;
    private final String prev;
    private final List<T> items;
}
