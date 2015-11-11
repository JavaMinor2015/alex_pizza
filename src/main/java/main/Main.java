package main;

import javax.persistence.Persistence;
import java.util.Properties;

/**
 * Created by alex on 11/11/15.
 */
public class Main {
    public static void main(String[] args) {
        Persistence.generateSchema("PizzaOracle", new Properties());
    }
}
