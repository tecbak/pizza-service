import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.domain.pizza.Type;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;

import java.util.Arrays;

public class SpringJpaAppRunner {
    public static void main(String[] args) {
        //repoContext
        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml");
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));

        //appContext
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);
        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));

        //done at lecture
        /*PizzaRepository pizzaRepository = appContext.getBean(PizzaRepository.class, "jpaPizzaRepository");
        Pizza pizza = new Pizza();
        pizza.setName("Sea");
        pizza.setType(Type.SEA);
        pizza = pizzaRepository.save(pizza);
        System.out.println(pizza.getId());*/

//        OrderRepository orderRepository = appContext.getBean(OrderService.class, "jpaOrderRepository");
//
//        orderRepository.save();

        OrderService orderService = appContext.getBean(OrderService.class, "orderService");
        PizzaRepository pizzaRepository = appContext.getBean(PizzaRepository.class, "jpaPizzaRepository");

        Pizza pizza = new Pizza();
        pizza.setName("Sea");
        pizza.setType(Type.SEA);
        pizza = pizzaRepository.save(pizza);

        Customer customer = new Customer();
        customer.setName("Peter");

        orderService.placeNewOrder(customer,  pizza.getId());
    }
}
