package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;


public class SimplePizzaService implements PizzaService {
    private PizzaRepository pizzaRepository;

    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
//        InitialContext context = new InitialContext();
//        pizzaRepository = context.getInstance("pizzaRepository");
    }

    @Override
    public Pizza find(Integer id) {
        return pizzaRepository.find(id);
    }

//    public static void main(String[] args) {
//        SimplePizzaService pizzaService = new SimplePizzaService();
//        System.out.println(pizzaService.find(1));
//    }
}
