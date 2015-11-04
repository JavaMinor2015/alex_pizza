package pizza.domain.beans;

import lombok.Getter;
import pizza.domain.Pizza;
import pizza.domain.Topping;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Arrays;

/**
 * Created by alex on 11/3/15.
 */
@Singleton
@Startup
public class ConfigBean {

    @Getter
    @EJB
    PizzaRequestBean pizzaRequestBean;

    @PostConstruct
    public void createData() {
        pizzaRequestBean.add(
                Pizza.builder()
                        .id(0)
                        .name("Margherita")
                        .toppings(
                                Arrays.asList(
                                        Topping.builder()
                                                .name("Peperoni")
                                                .extraPrice(1.0)
                                                .build()
                                        ,
                                        Topping.builder()
                                                .name("Onions")
                                                .extraPrice(0.5)
                                                .build())
                        )
                        .price(10.0)
                .build()
        );
        pizzaRequestBean.add(
                Pizza.builder()
                        .id(1)
                        .name("Hawaii")
                        .toppings(
                                Arrays.asList(
                                        Topping.builder()
                                                .name("Pineapple")
                                                .extraPrice(1.0)
                                                .build()
                                        ,
                                        Topping.builder()
                                                .name("Ham")
                                                .extraPrice(1.0)
                                                .build())
                        )
                        .price(10.0)
                        .build()
        );
    }
}
