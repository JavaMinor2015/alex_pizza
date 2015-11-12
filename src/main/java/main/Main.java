package main;

import javax.persistence.Persistence;
import java.util.Properties;

/**
 * Created by alex on 11/11/15.
 */
public final class Main {
    private Main() {

    }

    /**
     * Initializes a DB schema, not necessary to run.
     * @param args arguments
     */
    public static void main(String[] args) {
        Persistence.generateSchema("PizzaOracle", new Properties());
    }
}
