import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class JpaAppRunner {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager manager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

//        Pizza pizza = new Pizza(1L, "Bavarian", BigDecimal.valueOf(100), Type.MEAT);
//        manager.persist(pizza);


        System.out.println(manager.find(Pizza.class, 1L));

        transaction.commit();

        manager.close();
        entityManagerFactory.close();
    }
}
