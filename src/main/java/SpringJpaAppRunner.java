import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.domain.pizza.Type;
import ua.rd.pizzaservice.services.CustomerService;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.PizzaService;

import java.math.BigDecimal;

public class SpringJpaAppRunner {
    public static void main(String[] args) {
        //repoContext
        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml");
//        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));

        //appContext
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);
//        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));

        //done at lecture
//        PizzaRepository pizzaRepository = appContext.getBean(PizzaRepository.class, "jpaPizzaRepository");
//        Pizza save = new Pizza();
//        save.setName("Sea");
//        save.setType(Type.SEA);
//        save = pizzaRepository.save(save);
//        System.out.println(save.getId());

//        OrderRepository orderRepository = appContext.getBean(OrderService.class, "jpaOrderRepository");
//
//        orderRepository.save();

        CustomerService customerService = appContext.getBean(CustomerService.class, "simpleCustomerService");
        PizzaService pizzaService = appContext.getBean(PizzaService.class, "simpePizzaService");
        OrderService orderService = appContext.getBean(OrderService.class, "simpleOrderService");

        Customer customer = customerService.save("Friedrich", "Berlin", "Unter den Linden", "7A");
        Pizza seaPizza = pizzaService.save("Sea", BigDecimal.valueOf(100), Type.SEA);
        Pizza meatPizza = pizzaService.save("Margarita", BigDecimal.valueOf(90), Type.MEAT);
        Pizza vegetarianPizza = pizzaService.save("Vegetarian", BigDecimal.valueOf(50), Type.VEGETARIAN);
        Long[] pizzas = {seaPizza.getPizzaId(), meatPizza.getPizzaId(), meatPizza.getPizzaId(), vegetarianPizza.getPizzaId()};
        Order order = orderService.placeNewOrder(customer, pizzas);

        System.out.println(order);

        for (Order order1 : orderService.findAll()) {
            System.out.println(order1);
        }

//        PizzaRepository pizzaRepository = appContext.getBean(PizzaRepository.class, "jpaPizzaRepository");
//
//        Pizza save = new Pizza();
//        save.setName("Sea");
//        save.setType(Type.SEA);
//        save.setPrice(BigDecimal.valueOf(100));
//        save = pizzaRepository.save(save);
//
//        Customer customer = new Customer();
//        customer.setName("Peter");
//
//
//        Order order = orderService.placeNewOrder(customer, save.getId());
//
//        System.out.println(order);
    }
}
