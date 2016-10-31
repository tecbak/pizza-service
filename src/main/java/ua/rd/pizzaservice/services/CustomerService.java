package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.customer.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(String name, String city, String street, String building);

    List<Customer> findAll();

    Customer find(String name);
}
