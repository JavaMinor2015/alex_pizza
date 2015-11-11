package pizza.domain.beans;

import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.Topping;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;

/**
 * Created by alex on 11/9/15.
 */
@Singleton
@Startup
public class ConfigBean {
    @PersistenceContext(unitName = "PizzaOracle")
    private EntityManager em;

    /**
     * Initialize dummy data.
     */
    @PostConstruct
    public void init() {

        generateDummyData();
    }

    private void generateDummyData() {
        em.persist(
                Pizza.builder()
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

        em.persist(
                Pizza.builder()
                        .name("Hawaii")
                        .toppings(
                                Arrays.asList(
                                        Topping.builder()
                                                .name("Pineapple")
                                                .extraPrice(2.0)
                                                .build()
                                        ,
                                        Topping.builder()
                                                .name("Ham")
                                                .extraPrice(0.33)
                                                .build())
                        )
                        .price(10.0)
                        .build()
        );

        em.flush();

    }
}
