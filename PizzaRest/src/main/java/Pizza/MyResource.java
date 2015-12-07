
package Pizza;

import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

/** Example resource class hosted at the URI path "/myresource"
 */
/** Example resource class hosted at the URI path "/myresource"
 */
@ApplicationPath("/rest")
@Path("/myresource")
@Stateless
public class MyResource extends Application {

    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Produces("text/plain")
    public String getIt() {
        return "Hi there!";
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(MyResource.class);
        return classes;
    }
}
