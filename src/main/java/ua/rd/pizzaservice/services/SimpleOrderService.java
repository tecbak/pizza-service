package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrderService implements OrderService {
    private final OrderRepository orderRepository;        // = new InMemOrderRepository();
    private final PizzaService pizzaService;              // = new SimplePizzaService();

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }

    @Benchmark
    @Override
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        checkQuantityOfPizzas(pizzasID);

        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzasID) {
            pizzas.add(findPizzaById(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);

//        Order newOrder = new Order(customer, pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    protected Order createNewOrder() {
        throw new IllegalStateException("Container failed to create a new order");
//        return context.getBean("order", Order.class);
    }

    private void checkQuantityOfPizzas(Integer[] pizzasID) {
        if (pizzasID.length < 1 || pizzasID.length > 10)
            throw new IllegalArgumentException("Quantity of pizzas must be from 1 to 10");
    }

    private Pizza findPizzaById(Integer id) {
        return pizzaService.find(id);
    }

    private Order saveOrder(Order newOrder) {
        return orderRepository.saveOrder(newOrder);
    }
}
