package ua.rd.pizzaservice.infrastructure;

import ua.rd.pizzaservice.repository.InMemOrderRepository;
import ua.rd.pizzaservice.repository.InMemPizzaRepository;
import ua.rd.pizzaservice.services.SimpleOrderService;
import ua.rd.pizzaservice.services.SimplePizzaService;

import java.util.HashMap;
import java.util.Map;

public class JavaConfig implements Config {
    private Map<String, Class<?>> classes = new HashMap<String, Class<?>>() {{
        put("pizzaRepository", InMemPizzaRepository.class);
        put("orderRepository", InMemOrderRepository.class);
        put("orderService", SimpleOrderService.class);
        put("pizzaService", SimplePizzaService.class);
    }};

    @Override
    public Class<?> getImpl(String name) {
        return classes.get(name);
    }
}
