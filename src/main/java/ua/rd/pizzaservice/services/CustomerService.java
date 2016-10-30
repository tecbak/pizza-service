package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.customer.Customer;

public interface CustomerService {
    Customer save(String name, String city, String street, String building);
}
