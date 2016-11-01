package ua.rd.pizzaservice.services;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import ua.rd.pizzaservice.domain.customer.Address;
import ua.rd.pizzaservice.domain.customer.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.*;


public class SimpleCustomerServiceIT extends ServiceTestConfig {
    @Autowired
    private CustomerService customerService;

    @Test
    public void testFindAll() throws Exception {
        Address address0 = new Address();
        address0.setCity("Warsaw");
        address0.setStreet("Mickiewicz str");
        address0.setBuilding("10A");
        Customer customer0 = new Customer();
        customer0.setName("Adam");
        customer0.setAddress(address0);
        customer0.depositToLoyaltyCard(valueOf(100.00));

        Address address1 = new Address();
        address1.setCity("Roma");
        address1.setStreet("Via Torino");
        address1.setBuilding("15");
        Customer customer1 = new Customer();
        customer1.setName("Mary");
        customer1.setAddress(address1);
        customer1.depositToLoyaltyCard(valueOf(200.00));

        String insertAddress =
                "INSERT INTO addresses (id, city, street, building) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(insertAddress,
                1, address0.getCity(), address0.getStreet(), address0.getBuilding());
        jdbcTemplate.update(insertAddress,
                2, address1.getCity(), address1.getStreet(), address1.getBuilding());

        String insertCustomer =
                "INSERT INTO customers (id, loyalty_card_balance, name, address_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(insertCustomer,
                1, customer0.getLoyaltyCardBalance(), customer0.getName(), 1);
        jdbcTemplate.update(insertCustomer,
                2, customer1.getLoyaltyCardBalance(), customer1.getName(), 2);

        List<Customer> customers = customerService.findAll();
        Assert.assertEquals(customer0, customers.get(0));
        Assert.assertEquals(customer1, customers.get(1));
    }
}
