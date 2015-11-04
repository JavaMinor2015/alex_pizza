package pizza.domain.beans;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.Serializable;

/**
 * Created by alex on 11/3/15.
 */
@Singleton
@Startup
public class ConfigBean implements Serializable {

    @PostConstruct
    public void createData() {
        // carry on, nothing to do
    }

}
