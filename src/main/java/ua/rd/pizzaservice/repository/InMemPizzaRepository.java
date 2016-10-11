package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.Type;
import ua.rd.pizzaservice.infrastructure.Benchmark;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Repository
public class InMemPizzaRepository implements PizzaRepository {
    private Map<Integer, Pizza> pizzas = new HashMap<>();

    @PostConstruct
    public void init() {
        pizzas.put(1, new Pizza(1L, "Vegetarian", BigDecimal.valueOf(60), Type.VEGETARIAN));
        pizzas.put(2, new Pizza(2L, "Meat", BigDecimal.valueOf(80), Type.MEAT));
        pizzas.put(3, new Pizza(3L, "Sea", BigDecimal.valueOf(100), Type.SEA));
    }

    @Benchmark
    @Override
    public Pizza find(Integer id) {
        return pizzas.get(id);
    }
}
