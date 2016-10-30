package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.order.Order;

import java.util.List;

public interface OrderService {
    Order placeNewOrder(Customer customer, Long... pizzasID);

    List<Order> findAll();
}
