package ua.rd.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaCustomerRepository implements CustomerRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Customer find(Long id) {
        return manager.find(Customer.class, id);
    }

    @Override
    public Customer save(Customer customer) {
        return manager.merge(customer);
    }
}
