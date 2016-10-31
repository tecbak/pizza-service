package ua.rd.pizzaservice.services.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.domain.pizza.Type;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.PizzaService;

import java.math.BigDecimal;

@Service("simplePizzaService")
public class SimplePizzaService implements PizzaService {
    private PizzaRepository pizzaRepository;

    @Autowired
    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza find(Long id) {
        return pizzaRepository.find(id);
    }

    @Override
    @Transactional
    public Pizza save(String name, BigDecimal price, Type type) {
        Pizza pizza = createNewPizza();
        pizza.setName(name);
        pizza.setPrice(price);
        pizza.setType(type);

        return pizzaRepository.save(pizza);
    }

    @Lookup
    protected Pizza createNewPizza() {
        throw new IllegalStateException("Container failed to create a new pizza");
    }
}
