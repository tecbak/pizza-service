package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("jpaPizzaRepository")
public class JpaPizzaRepository implements PizzaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pizza find(Integer id) {
        return entityManager.find(Pizza.class, id);
    }

    @Override
    @Transactional //starts transaction at the start of method and commits at its end (or rollbacks)
    public Pizza save(Pizza pizza) {
        Pizza newPizza = entityManager.merge(pizza);
        return newPizza;
    }
}
