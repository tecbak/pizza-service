package ua.rd.pizzaservice.domain;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.domain.pizza.Pizza;
import ua.rd.pizzaservice.domain.pizza.Type;
import ua.rd.pizzaservice.repository.InMemPizzaRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;

public class OrderTest {
    private Order order;
    private PizzaRepository pizzaRepository;
    private Pizza vegeterian;
    private Pizza meat;
    private Pizza sea;
    private HashMap<Pizza, Integer> pizzas;
    private Customer customer;


    @Before
    public void setUp() throws Exception {
        pizzaRepository = new InMemPizzaRepository();
        vegeterian = new Pizza(1L, "Vegetarian", BigDecimal.valueOf(60), Type.VEGETARIAN);
        meat = new Pizza(2L, "Meat", BigDecimal.valueOf(80), Type.MEAT);
        sea = new Pizza(3L, "Sea", BigDecimal.valueOf(100), Type.SEA);
        pizzas = new HashMap<Pizza, Integer>() {{
            put(vegeterian, 2);
            put(meat, 2);
            put(sea, 1);
//            add(vegeterian);
//            add(vegeterian);
//            add(meat);
//            add(meat);
//            add(sea);
        }};
        customer = new Customer();
        order = new Order(customer, pizzas);
    }

    @Test
    public void testGetPrice() throws Exception {
        BigDecimal expected = sea.getPrice(). //multiply(BigDecimal.valueOf(0.7)).
                add(vegeterian.getPrice().add(vegeterian.getPrice()).
                add(meat.getPrice()).add(meat.getPrice()));
        BigDecimal actual = order.getPrice();

        assertEquals(expected, actual);
    }

    @Ignore
    @Test
    public void getDiscountTest() throws Exception {
//        BigDecimal expected = sea.getPrice().multiply(BigDecimal.valueOf(0.3));
//        BigDecimal actual = order.getQuantityDiscount();
//
//        assertEquals(expected, actual);
    }

    @Ignore
    @Test
    public void setStatusTest() throws Exception {
//        order.setStatus(Status.IN_PROGRESS);
//        order.setStatus(Status.DONE);
    }

    @Ignore
    @Test(expected = RuntimeException.class)
    public void onSettingStatusInProgressAfterStatusCancelled_throwException() throws Exception {
//        order.setStatus(Status.CANCELLED);
//        order.setStatus(Status.IN_PROGRESS);
    }

    @Ignore
    @Test
    public void getPriceWithDiscountsTest() {
//        customer.depositToLoyaltyCard(BigDecimal.valueOf(2000));
//
//        System.out.println(order.getTotalDiscount());
//        System.out.println(order.getLoyaltyCardDiscount());
//        System.out.println(order.getQuantityDiscount());
    }
}