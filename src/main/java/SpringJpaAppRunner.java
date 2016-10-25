import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.Type;
import ua.rd.pizzaservice.repository.JpaPizzaRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;

import java.util.Arrays;

public class SpringJpaAppRunner {
    public static void main(String[] args) {
        //repoContext
        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml");
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));

        //appContext
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);
        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));

        PizzaRepository pizzaRepository = appContext.getBean(PizzaRepository.class, "jpaPizzaRepository");
        Pizza pizza = new Pizza();
        pizza.setName("Sea");
        pizza.setType(Type.SEA);
        pizza = pizzaRepository.save(pizza);
        System.out.println(pizza.getId());
    }
}
