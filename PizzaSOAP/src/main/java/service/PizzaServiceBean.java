package service;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by alex on 11/16/15.
 */
@Stateless
@WebService
public class PizzaServiceBean {
    @WebMethod
    public String getPizzas() {
        return "pizza";
    }
}
