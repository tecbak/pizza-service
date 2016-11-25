package ua.rd.pizzaservice.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.services.PizzaService;

@Controller
public class PizzaController {

    @Qualifier("simplePizzaService")
    @Autowired
    private PizzaService pizzaService;

    @RequestMapping("/hello")
//    @ResponseBody
    public ModelAndView hello(ModelAndView mv) {
        mv.setViewName("hello");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("pizzas", pizzaService.findAll());
        return mv;
    }

    @RequestMapping("/edit/pizza/{pizzaId}")
//    @ResponseBody
    public ModelAndView edit(@PathVariable(name = "pizzaId") Long pizzaId, ModelAndView mv) {
        mv.setViewName("hello");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("message", "you want to edit pizza #" + pizzaId);
        return mv;
    }

    @RequestMapping("/create")
    @Secured("ROLE_USER")
    public String create() {
        return "create";
    }

    @RequestMapping("/exception")
    public void exception() {
        throw new NumberFormatException();
    }

    @RequestMapping(name = "/add/pizza", method = RequestMethod.POST)
    public String addNew(@ModelAttribute Pizza pizza) {

        return "newPizza";
    }
}
