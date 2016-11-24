package ua.rd.pizzaservice.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.services.PizzaService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PizzaRestController {
    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(
            value = "/pizza/{pizzaId}",
            method = RequestMethod.GET)
    public ResponseEntity<Pizza> find(@PathVariable("pizzaId") Long pizzaId) {
        Pizza pizza = pizzaService.find(pizzaId);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Link link = linkTo(methodOn(PizzaRestController.class).find(pizzaId)).withSelfRel();
        pizza.add(link);

        return new ResponseEntity<>(pizza, HttpStatus.FOUND);


//        return new Pizza(1L, "sdsd", BigDecimal.valueOf(100), Type.MEAT);

//        return "<marquee behavior=\"alternate\" direction=\"right\">" +
//                "<h1><b>Hello!</b></h1>" +
//                "</marquee>";
    }

    @RequestMapping(
            value = "/pizza",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<Void> save(@RequestBody Pizza pizza, UriComponentsBuilder builder) {
        System.out.println(pizza);
        Pizza p = pizzaService.save(pizza);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/save/{id}").buildAndExpand(p.getPizzaId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
