package ua.rd.pizzaservice.repository.inmem;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class InMemOrderRepository implements OrderRepository {
    private List<Order> orders = new ArrayList<>();

    @Override
    public Order find(Long id) {
        throw new UnsupportedOperationException();
    }

    public Order save(Order order) {
        orders.add(order);
        return order;
    }
}