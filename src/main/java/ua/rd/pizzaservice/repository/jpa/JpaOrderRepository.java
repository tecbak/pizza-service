package ua.rd.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("jpaOrderRepository")
public class JpaOrderRepository implements OrderRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Order find(Long id) {
        return manager.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        TypedQuery<Order> query = manager.createNamedQuery("Order.findAll", Order.class);
        return query.getResultList();
    }

    @Override
    public Order save(Order order) {
        return manager.merge(order);
    }
}
