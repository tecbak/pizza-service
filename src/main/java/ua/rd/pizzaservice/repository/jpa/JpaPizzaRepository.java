package ua.rd.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("jpaPizzaRepository")
public class JpaPizzaRepository implements PizzaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pizza find(Long id) {
        return entityManager.find(Pizza.class, id);
    }

    @Override
    @Transactional //starts transaction at the start of method and commits at its end (or rollbacks)
    public Pizza save(Pizza pizza) {
        return entityManager.merge(pizza);
    }
}
