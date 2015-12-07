package api.main;

import api.controller.PizzaRestController;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by alex on 12/7/15.
 */
@ApplicationPath("/rest")
public class RestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // TODO add endpoint implementations as they come
        classes.add(PizzaRestController.class);
        return classes;
    }
}
