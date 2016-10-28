package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.order.Order;

public interface OrderRepository {
    Order saveOrder(Order order);
}
