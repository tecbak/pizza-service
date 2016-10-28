package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.order.Order;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemOrderRepository implements OrderRepository {
    private List<Order> orders = new ArrayList<>();

    public Order saveOrder(Order order) {
        orders.add(order);
        return order;
    }
}
