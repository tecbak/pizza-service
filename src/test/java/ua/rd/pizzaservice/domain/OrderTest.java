package ua.rd.pizzaservice.domain;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.repository.InMemPizzaRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest {
    private Order order;
    private PizzaRepository pizzaRepository;
    private Pizza pizza;
    private Pizza meat;
    private Pizza sea;
    private List<Pizza> pizzas;
    private Customer customer;


    @Before
    public void setUp() throws Exception {
        pizzaRepository = new InMemPizzaRepository();
        pizza = new Pizza(1L, "Vegetarian", BigDecimal.valueOf(60), Type.VEGETARIAN);
        meat = new Pizza(2L, "Meat", BigDecimal.valueOf(80), Type.MEAT);
        sea = new Pizza(3L, "Sea", BigDecimal.valueOf(100), Type.SEA);
        pizzas = new ArrayList<Pizza>() {{
            add(pizza);
            add(pizza);
            add(meat);
            add(meat);
            add(sea);
        }};
        customer = new Customer();
        order = new Order(null, pizzas);
    }

    @Test
    public void testGetPrice() throws Exception {
        BigDecimal expected = sea.getPrice(). //multiply(BigDecimal.valueOf(0.7)).
                add(pizza.getPrice().add(pizza.getPrice()).
                add(meat.getPrice()).add(meat.getPrice()));
        BigDecimal actual = order.getPrice(false);

        assertEquals(expected, actual);
    }

    @Test
    public void getDiscountTest() throws Exception {
        BigDecimal expected = sea.getPrice().multiply(BigDecimal.valueOf(0.3));
        BigDecimal actual = order.getDiscount();

        assertEquals(expected, actual);
    }

    @Test
    public void setStatusTest() throws Exception {
        order.setStatus(Statuses.IN_PROGRESS);
        order.setStatus(Statuses.DONE);
    }

    @Test(expected = RuntimeException.class)
    public void onSettingStatusInProgressAfterStatusCancelled_throwException() throws Exception {
        order.setStatus(Statuses.CANCELLED);
        order.setStatus(Statuses.IN_PROGRESS);
    }
}