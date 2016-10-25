package ua.rd.pizzaservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.Type;

import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:repoContext.xml")
//@Transactional
//@Rollback
public class JpaPizzaRepositoryIT extends RepositoryTestConfig {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
//    @Rollback
    public void testSavePizza() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setName("Sea");
        pizza.setType(Type.SEA);
        pizza = pizzaRepository.save(pizza);
        System.out.println(pizza.getId());

        assertNotNull(pizza.getId());
    }
}