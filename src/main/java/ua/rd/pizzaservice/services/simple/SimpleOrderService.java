package ua.rd.pizzaservice.services.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Service("simpleOrderService")
public class SimpleOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }

    @Benchmark(false)
    @Override
    @Transactional
    public Order placeNewOrder(Customer customer, Long... pizzasID) {
        checkQuantityOfPizzas(pizzasID);

        List<Pizza> pizzas = new ArrayList<>();
        for (Long id : pizzasID) {
            pizzas.add(pizzaService.find(id));
        }

        Order order = createNewOrder();
        order.setCustomer(customer);
        order.setPizzas(listToMap(pizzas));

        return orderRepository.save(order);
    }

    @Lookup
    protected Order createNewOrder() {
        throw new IllegalStateException("Container failed to save a new order");
    }

    private void checkQuantityOfPizzas(Long[] pizzasID) {
        if (pizzasID.length < 1 || pizzasID.length > 10)
            throw new IllegalArgumentException("Quantity of pizzas must be from 1 to 10");
    }

    private <E> Map<E, Integer> listToMap(List<E> list) {
        Map<E, Integer> map = new HashMap<>();
        for (E element : list) {
            if (map.containsKey(element)) {
                int quantity = map.get(element);
                map.put(element, quantity + 1);
            } else {
                map.put(element, 1);
            }
        }
        return map;
    }
}
