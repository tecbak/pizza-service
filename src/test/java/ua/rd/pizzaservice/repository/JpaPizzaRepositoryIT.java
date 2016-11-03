package ua.rd.pizzaservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.domain.pizza.Type;

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
        System.out.println(pizza.getPizzaId());

        assertNotNull(pizza.getPizzaId());
    }
}