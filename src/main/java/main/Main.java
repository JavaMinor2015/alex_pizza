package main;

import java.util.Properties;

import javax.persistence.Persistence;

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
