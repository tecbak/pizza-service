package ua.rd.pizzaservice.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.domain.pizza.Type;
import ua.rd.pizzaservice.services.PizzaService;
import ua.rd.pizzaservice.services.simple.SimplePizzaService;

import java.math.BigDecimal;

@RestController
public class PizzaRestController {
    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(value = "/pizza/{pizzaId}", method = RequestMethod.GET)
    public Pizza hello(@PathVariable("pizzaId") Long pizzaId) {
        return pizzaService.find(pizzaId);

//        return new Pizza(1L, "sdsd", BigDecimal.valueOf(100), Type.MEAT);

//        return "<marquee behavior=\"alternate\" direction=\"right\">" +
//                "<h1><b>Hello!</b></h1>" +
//                "</marquee>";
    }

    @RequestMapping(value = "/pizza", method = RequestMethod.POST)
    public void pizza(@RequestBody Pizza pizza) {
        System.out.println(pizza);
    }
}
