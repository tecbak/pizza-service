package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.pizza.Pizza;

public interface PizzaService {
    Pizza find(Integer id);
}
