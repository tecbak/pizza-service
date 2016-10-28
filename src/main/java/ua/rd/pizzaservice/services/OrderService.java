package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.order.Order;

public interface OrderService {
     Order placeNewOrder(Customer customer, Integer... pizzasID);
}
