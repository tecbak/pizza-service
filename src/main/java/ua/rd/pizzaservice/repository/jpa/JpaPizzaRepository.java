package ua.rd.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("jpaPizzaRepository")
public class JpaPizzaRepository implements PizzaRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Pizza find(Long id) {
        return manager.find(Pizza.class, id);
    }

    @Override
    public List<Pizza> findAll() {
        TypedQuery<Pizza> query = manager.createNamedQuery("Pizza.findAll", Pizza.class);
        return query.getResultList();
    }

    @Override
    public Pizza save(Pizza pizza) {
        return manager.merge(pizza);
    }
}
