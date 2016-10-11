package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

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
    public Pizza find(Integer id) {
        return pizzaRepository.find(id);
    }
}
