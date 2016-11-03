package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.domain.pizza.Type;

import java.math.BigDecimal;

public interface PizzaService {
    Pizza find(Long id);

    Pizza save(String name, BigDecimal price, Type type);

    Pizza save(Pizza pizza);
}
