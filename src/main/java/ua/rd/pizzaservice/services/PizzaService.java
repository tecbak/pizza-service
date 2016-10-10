package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;

public interface PizzaService {
    Pizza find(Integer id);
}
