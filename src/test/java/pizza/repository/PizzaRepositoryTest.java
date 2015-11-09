package pizza.repository;

import org.junit.Before;
import org.junit.Test;
import pizza.domain.concrete.persist.Pizza;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 11/9/15.
 */
public class PizzaRepositoryTest {

    private PizzaRepository repository;

    @Before
    public void setUp(){
        repository = new PizzaRepository();
    }

    @Test
    public void testAdd() throws Exception {
        Pizza p = new Pizza(1L, "PIzza", null, 0.5D);
        repository.add(p);
        assertThat(repository.getAll().contains(p), is(true));
    }

    @Test
    public void testLoad() throws Exception {
        repository.load();
    }

    @Test
    public void testSave() throws Exception {
        repository.save();
    }
}