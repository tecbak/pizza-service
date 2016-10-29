package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.order.Order;

public interface OrderService {
     Order placeNewOrder(Customer customer, Long... pizzasID);
}
