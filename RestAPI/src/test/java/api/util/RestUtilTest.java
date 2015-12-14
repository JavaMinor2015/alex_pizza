package api.util;

import api.domain.RestDecorator;
import api.domain.abs.HateoasResponse;
import api.error.RestException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pizza.domain.concrete.persist.Pizza;
import pizza.rules.Globals;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Generated 4-12-2015.
 *
 * @author Alex
 */
public class RestUtilTest {
    public static final String URL = "http://api/woops";
    public static final String HTTP_API_WOOPS = "http://api/woops?";
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private HateoasResponse<RestDecorator> response;

    private RestDecorator<Pizza> testObj;
    private List<RestDecorator> objList;

    @Before
    public void setUp() throws Exception {
        testObj = new RestDecorator<>("http://api/woops/1", new Pizza());
        objList = Arrays
                .asList(testObj, testObj, testObj, testObj, testObj, testObj);
    }

    @Test
    public void testBuildResponse() throws Exception {

    }

    @Test
    public void testHateoasSingle() throws Exception {
        response = RestUtil.createHateoas(testObj, "", 0, 6);
        assertThat(response.getItems().size(), is(1));
    }

    @Test
    public void testHateoasSingleNull() throws Exception {
        testObj = null;
        thrown.expect(RestException.class);
        RestUtil.createHateoas(testObj, "", 0, 1);
    }

    @Test
    public void testHateoasSingleStart() throws Exception {
        thrown.expect(RestException.class);
        RestUtil.createHateoas(testObj, "", -1, 1);
    }

    @Test
    public void testHateoasSingleLimit() throws Exception {
        thrown.expect(RestException.class);
        RestUtil.createHateoas(testObj, "", 1, 0);
    }

    @Test
    public void testHateoasMulti() throws Exception {
        response = RestUtil.createHateoas(objList, "", 0, 6);
        assertThat(response.getItems().size(), is(6));
    }

    @Test
    public void testHateoasMultiEmpty() throws Exception {
        thrown.expect(RestException.class);
        RestUtil.createHateoas(new ArrayList<>(), "", 0, 1);
    }

    @Test
    public void testHateoasMultiNull() throws Exception {
        List<RestDecorator> list = null;
        thrown.expect(RestException.class);
        RestUtil.createHateoas(list, "", 0, 1);
    }

    @Test
    public void testHateoasMultiStart() throws Exception {
        thrown.expect(RestException.class);
        RestUtil.createHateoas(objList, "", -1, 1);
    }

    @Test
    public void testHateoasMultiLimit() throws Exception {
        thrown.expect(RestException.class);
        RestUtil.createHateoas(objList, "", 1, 0);
    }

    @Test
    public void testHateoasSelf() throws Exception {
        // test with one item

        // start at 0, limit of 1, item size of 1
        // self should reflect that
        response = RestUtil.createHateoas(testObj, URL, 0, 1);
        assertThat(response.getSelf(), is(HTTP_API_WOOPS + Globals.PAGE_DELIMITER + "=0&" + Globals.PER_PAGE_DELIMITER + "=1"));

        // start at 0, limit of 10, item size of 1
        // self should have limit of 1
        response = RestUtil.createHateoas(testObj, URL, 0, 10);
        assertThat(response.getSelf(), is(HTTP_API_WOOPS + Globals.PAGE_DELIMITER + "=0&" + Globals.PER_PAGE_DELIMITER + "=1"));

        // start at 1, limit of 1, item size of 1
        // self should reflect that
        response = RestUtil.createHateoas(testObj, URL, 1, 1);
        assertThat(response.getSelf(), is(HTTP_API_WOOPS + Globals.PAGE_DELIMITER + "=1&" + Globals.PER_PAGE_DELIMITER + "=1"));

        // start at 1, limit of 10, item size of 1
        // self should have limit of 1
        response = RestUtil.createHateoas(testObj, URL, 1, 10);
        assertThat(response.getSelf(), is(HTTP_API_WOOPS + Globals.PAGE_DELIMITER + "=1&" + Globals.PER_PAGE_DELIMITER + "=1"));

        // limit at 0
        // exception should be thrown
        thrown.expect(RestException.class);
        response = RestUtil.createHateoas(testObj, URL, 1, 0);
    }

    @Test
    public void testHateoasNext() throws Exception {

        // test with one item

        // start at 0, limit at 1, item size of 1
        // next should start at 1
        response = RestUtil.createHateoas(testObj, URL, 0, 1);
        assertThat(response.getNext(), is(HTTP_API_WOOPS + Globals.PAGE_DELIMITER + "=1&" + Globals.PER_PAGE_DELIMITER + "=1"));

        // start at 0, limit at 10, item size of 1
        // there is no next if limit is not reached
        response = RestUtil.createHateoas(testObj, URL, 0, 10);
        assertThat(response.getNext(), is(""));

        // start at 1, limit at 1, item size of 1
        // next should start at 2
        response = RestUtil.createHateoas(testObj, URL, 1, 1);
        assertThat(response.getNext(), is(HTTP_API_WOOPS + Globals.PAGE_DELIMITER + "=2&" + Globals.PER_PAGE_DELIMITER + "=1"));
    }

    @Test
    public void testHateoasPrev() throws Exception {

        // test with one item

        // start at 0, limit at 1, item size of 1
        // prev should be empty
        response = RestUtil.createHateoas(testObj, URL, 0, 1);
        assertThat(response.getPrev(), is(""));

        // start 1, limit at 1, item size of 1
        // prev should start at 0
        response = RestUtil.createHateoas(testObj, URL, 1, 1);
        assertThat(response.getPrev(), is(HTTP_API_WOOPS + Globals.PAGE_DELIMITER + "=0&" + Globals.PER_PAGE_DELIMITER + "=1"));

        // start at 1, limit at 5, item size of 1
        // prev should start at 0
        response = RestUtil.createHateoas(testObj, URL, 1, 5);
        assertThat(response.getPrev(), is(HTTP_API_WOOPS + Globals.PAGE_DELIMITER + "=0&" + Globals.PER_PAGE_DELIMITER + "=5"));
    }
}