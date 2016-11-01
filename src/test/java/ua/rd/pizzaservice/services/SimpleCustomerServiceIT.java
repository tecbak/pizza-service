package ua.rd.pizzaservice.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import ua.rd.pizzaservice.domain.customer.Address;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.repository.CustomerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.math.BigDecimal.*;


public class SimpleCustomerServiceIT extends ServiceTestConfig {
    private static class CustomerRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            throw new UnsupportedOperationException();
        }
    }

    @Autowired
    private CustomerService customerService;
    private Address address0;
    private Customer customer0;
    private Address address1;
    private Customer customer1;

    @Before
    public void setUp() throws Exception {
        address0 = new Address();
        address0.setCity("Warsaw");
        address0.setStreet("Mickiewicz str");
        address0.setBuilding("10A");
        customer0 = new Customer();
        customer0.setName("Adam");
        customer0.setAddress(address0);
        customer0.depositToLoyaltyCard(valueOf(100.00));

        address1 = new Address();
        address1.setCity("Roma");
        address1.setStreet("Via Torino");
        address1.setBuilding("15");
        customer1 = new Customer();
        customer1.setName("Mary");
        customer1.setAddress(address1);
        customer1.depositToLoyaltyCard(valueOf(200.00));
    }

    @Test
    public void testFindAll() throws Exception {
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

    @Test
    public void testSave() throws Exception {
        customerService.save(customer0.getName(), address0.getCity(), address0.getStreet(), address0.getBuilding());
        String selectCustomer = "SELECT ";
        Customer actual = (Customer) jdbcTemplate.queryForObject(selectCustomer, new Object[]{customer0.getName()}, new CustomerRowMapper());
    }
}
