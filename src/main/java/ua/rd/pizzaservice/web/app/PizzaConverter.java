package ua.rd.pizzaservice.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.services.PizzaService;


public class PizzaConverter implements Converter<String, Pizza> {

    @Autowired
    private PizzaService pizzaService;

    @Override
    public Pizza convert(String pizzaIdStr) {
        if (pizzaIdStr == null || pizzaIdStr.isEmpty()) {
            return null;
        }
        Long pizzaId = Long.valueOf(pizzaIdStr);
        if (pizzaId != null) {
            return pizzaService.find(pizzaId);
        }
        return null;
    }
}
