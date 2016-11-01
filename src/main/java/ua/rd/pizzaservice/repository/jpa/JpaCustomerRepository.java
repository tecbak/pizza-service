package ua.rd.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("jpaCustomerRepository")
public class JpaCustomerRepository implements CustomerRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Customer find(Long id) {
        return manager.find(Customer.class, id);
    }

    @Override
    public Customer find(String name) {
        TypedQuery<Customer> query = manager.createNamedQuery("Customer.findByName", Customer.class);
        return query.setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = manager.createNamedQuery("Customer.findAll", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer save(Customer customer) {
        return manager.merge(customer);
    }
}
