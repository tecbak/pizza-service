package ua.rd.pizzaservice.services;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.repository.inmem.InMemOrderRepository;
import ua.rd.pizzaservice.repository.inmem.InMemPizzaRepository;
import ua.rd.pizzaservice.repository.OrderRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;

public class SimpleOrderServiceTest {
    private PizzaRepository pizzaRepository;
    private OrderService orderService;
    private PizzaService pizzaService;
    private OrderRepository orderRepository;

    @Before
    public void setUp() throws Exception {
        pizzaRepository = new InMemPizzaRepository();
        orderRepository = new InMemOrderRepository();
        pizzaService = new SimplePizzaService(pizzaRepository);
        orderService = new SimpleOrderService(orderRepository, pizzaService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void onQuantityOfPizzaGreaterThen10_throwNewException() throws Exception {
        Integer[] elevenPizzas = new Integer[11];
        orderService.placeNewOrder(null, elevenPizzas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void onZeroQuantityOfPizzas_throwNewException() throws Exception {
        Integer[] zeroPizzas = new Integer[0];
        orderService.placeNewOrder(null, zeroPizzas);
    }




}