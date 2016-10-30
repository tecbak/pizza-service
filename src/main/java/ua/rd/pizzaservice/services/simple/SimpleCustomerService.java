package ua.rd.pizzaservice.services.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.customer.Address;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.repository.CustomerRepository;
import ua.rd.pizzaservice.services.CustomerService;

import java.util.List;

@Service("simpleCustomerService")
public class SimpleCustomerService implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public SimpleCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer save(String name, String city, String street, String building) {
        Address address = createNewAddress();
        address.setCity(city);
        address.setStreet(street);
        address.setBuilding(building);

        Customer customer = createNewCustomer();
        customer.setName(name);
        customer.setAddress(address);

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer find(String name) {
        return customerRepository.find(name);
    }

    @Lookup
    protected Customer createNewCustomer() {
        throw new IllegalStateException("Container failed to create a new customer");
    }

    @Lookup
    protected Address createNewAddress() {
        throw new IllegalStateException("Container failed to create a new address");
    }
}
