package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.pizza.Pizza;

public interface PizzaRepository {
    Pizza find(Integer id);

    Pizza save(Pizza pizza);
}
