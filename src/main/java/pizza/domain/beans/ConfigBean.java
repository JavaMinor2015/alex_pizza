package pizza.domain.beans;

import java.security.SecureRandom;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pizza.domain.concrete.persist.Ingredient;
import pizza.domain.concrete.persist.Pizza;

/**
 * Created by alex on 11/9/15.
 */
@Singleton
@Startup
public class ConfigBean {
    @PersistenceContext(unitName = "PizzaOracle")
    private EntityManager em;

    private static final int AMOUNT = 100;
    private final SecureRandom r = new SecureRandom();

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
                .amount(r.nextInt(AMOUNT))
                .measurementType(Ingredient.MeasurementType.GRAM)
                .extraPrice(r.nextDouble() * AMOUNT)
                .build();
        Ingredient sauce = Ingredient.builder()
                .name("Tomato Sauce")
                .amount(r.nextInt(AMOUNT))
                .measurementType(Ingredient.MeasurementType.MILLILITER)
                .extraPrice(r.nextDouble() * AMOUNT)
                .build();
        Ingredient pineapple = Ingredient.builder()
                .name("Pineapple")
                .amount(r.nextInt(AMOUNT))
                .measurementType(Ingredient.MeasurementType.GRAM)
                .extraPrice(r.nextDouble() * AMOUNT)
                .build();
        Ingredient ham = Ingredient.builder()
                .name("Ham")
                .amount(r.nextInt(AMOUNT))
                .measurementType(Ingredient.MeasurementType.GRAM)
                .extraPrice(r.nextDouble() * AMOUNT)
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
                        .price(r.nextDouble() * AMOUNT)
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
                        .price(r.nextDouble() * AMOUNT)
                        .build()
        );

        em.flush();

    }
}
