package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Order;

public interface OrderRepository {
    Order saveOrder(Order order);
}
