import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.repository.OrderRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.PizzaService;
import ua.rd.pizzaservice.services.SomeService;

import java.util.Arrays;

public class SpringAppRunner {
    public static void main(String[] args) {
        //repoContext
        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml");
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));

        //appContext
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);
        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));

        //show t1 from different contexts
//        System.out.println(repoContext.getBean("t1", SomeService.class).getString());
//        System.out.println(appContext.getBean("t1", SomeService.class).getString());
//
//
//        PizzaRepository pizzaRepository = repoContext.getBean("pizzaRepository", PizzaRepository.class);
//        System.out.println(pizzaRepository.find(1));
//
//        OrderRepository orderRepository = repoContext.getBean("orderRepository", OrderRepository.class);
//        OrderService orderService = repoContext.getBean("orderService", OrderService.class);
//        PizzaService pizzaService = repoContext.getBean("pizzaService", PizzaService.class);
//
//        Order order = orderService.placeNewOrder(null, 1, 2, 3);
//        System.out.println(order);




        appContext.close();
        repoContext.close();


    }
}
