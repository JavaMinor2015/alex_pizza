package Pizza.controller;

import Pizza.adapters.PizzaDeserializer;
import Pizza.model.PizzaJson;
import Pizza.rest.RestUtil;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import util.PageMapping;

//import pizza.repository.PizzaRepository;

/**
 * Created by alex on 12/14/15.
 */
@Controller
public class PizzaController {
//
//    @Autowired
//    PizzaRepository pizzaRepository;

    @RequestMapping(PageMapping.PIZZA_URL)
    public String pizza(final Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String pizzaString = restTemplate.getForObject("http://localhost:8080/RestAPI_war_exploded/rest/pizzas/1", String.class);

//        pizzaRepository.getAll().get(0);

        GsonBuilder builder = RestUtil.getBuilder(new PizzaDeserializer());
//        model.addAttribute("pizzaJson", pizzaRepository.getAll().get(0));
        model.addAttribute("pizzaJson", builder.create().fromJson(pizzaString, PizzaJson.class));
        return PageMapping.PIZZA_PAGE;
    }

}
