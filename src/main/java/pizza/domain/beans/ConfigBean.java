package pizza.domain.beans;

import pizza.domain.concrete.persist.Pizza;
import pizza.domain.concrete.persist.Ingredient;

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
        Ingredient peperoni = Ingredient.builder()
                .name("Peperoni")
                .amount(100)
                .measurementType(Ingredient.MeasurementType.GRAM)
                .extraPrice(1.0)
                .build();
        Ingredient sauce = Ingredient.builder()
                .name("Tomato Sauce")
                .amount(200)
                .measurementType(Ingredient.MeasurementType.MILLILITER)
                .extraPrice(0.5)
                .build();
        Ingredient pineapple = Ingredient.builder()
                .name("Pineapple")
                .amount(50)
                .measurementType(Ingredient.MeasurementType.GRAM)
                .extraPrice(2.0)
                .build();
        Ingredient ham = Ingredient.builder()
                .name("Ham")
                .amount(10)
                .measurementType(Ingredient.MeasurementType.GRAM)
                .extraPrice(0.33)
                .build();

        em.persist(
                Pizza.builder()
                        .name("Margherita")
                        .ingredients(
                                Arrays.asList(
                                        peperoni,
                                        sauce
                                )
                        )
                        .price(10.0)
                        .build()
        );

        em.persist(
                Pizza.builder()
                        .name("Hawaii")
                        .ingredients(
                                Arrays.asList(
                                        pineapple,
                                        ham,
                                        sauce
                                )
                        )
                        .price(10.0)
                        .build()
        );

        em.flush();

    }
}
