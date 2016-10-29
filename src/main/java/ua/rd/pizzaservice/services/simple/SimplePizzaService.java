package ua.rd.pizzaservice.services.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.PizzaService;

@Service
public class SimplePizzaService implements PizzaService {
    private PizzaRepository pizzaRepository;

    @Autowired
    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
//        InitialContext context = new InitialContext();
//        pizzaRepository = context.getInstance("pizzaRepository");
    }

    @Override
    public Pizza find(Long id) {
        return pizzaRepository.find(id);
    }
}
