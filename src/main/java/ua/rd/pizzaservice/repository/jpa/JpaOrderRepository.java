package ua.rd.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("jpaOrderRepository")
public class JpaOrderRepository implements OrderRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Order find(Long id) {
        return manager.find(Order.class, id);
    }

    @Override
    public Order save(Order order) {
        return manager.merge(order);
    }
}
