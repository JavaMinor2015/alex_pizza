package Pizza.controller;

import Pizza.model.PizzaJson;
import Pizza.rest.RestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import util.PageMapping;

/**
 * Created by alex on 12/14/15.
 */
@Controller
public class PizzaController {

    @RequestMapping(PageMapping.PIZZA_URL)
    public String pizza(final Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String pizzaString = restTemplate.getForObject("http://localhost:8080/RestAPI_war_exploded/rest/pizzas/1", String.class);

        PizzaJson pizzaJson = RestUtil.createFromJson(pizzaString);
        model.addAttribute("pizzaJson", pizzaJson);
        return PageMapping.PIZZA_PAGE;
    }

}
