package ua.rd.pizzaservice.services.simple;

import org.springframework.beans.factory.annotation.Autowired;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.OrderRepository;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.PizzaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Order placeNewOrder(Customer customer, Long... pizzasID) {
        checkQuantityOfPizzas(pizzasID);

        List<Pizza> pizzas = new ArrayList<>();

        for (Long id : pizzasID) {
            pizzas.add(findPizzaById(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(listToMap(pizzas));

//        Order newOrder = new Order(customer, pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    protected Order createNewOrder() {
        throw new IllegalStateException("Container failed to create a new order");
//        return context.getBean("order", Order.class);
    }

    private void checkQuantityOfPizzas(Long[] pizzasID) {
        if (pizzasID.length < 1 || pizzasID.length > 10)
            throw new IllegalArgumentException("Quantity of pizzas must be from 1 to 10");
    }

    private Pizza findPizzaById(Long id) {
        return pizzaService.find(id);
    }

    private Order saveOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    private <E> Map<E, Integer> listToMap(List<E> list) {
        Map<E, Integer> map = new HashMap<>();
        for (E element : list) {
            if (map.containsKey(element)) {
                int quantity = map.get(element);
                map.put(element, quantity + 1);
            }
        }
        return map;
    }
}
