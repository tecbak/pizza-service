package ua.rd.pizzaservice.services;

import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.domain.pizza.Type;

import java.math.BigDecimal;
import java.util.List;

public interface PizzaService {
    Pizza find(Long id);

    @Transactional
    List<Pizza> findAll();

    Pizza save(String name, BigDecimal price, Type type);

    Pizza save(Pizza pizza);
}
