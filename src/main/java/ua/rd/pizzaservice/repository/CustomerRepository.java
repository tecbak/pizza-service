package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.customer.Customer;

public interface CustomerRepository extends Repository<Customer> {
    Customer find(String name);

//    Customer save(Long id);
//
//    Customer save(Customer customer);
}
