package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;

public interface PizzaRepository {
    Pizza find(Integer id);

    Pizza save(Pizza pizza);
}
