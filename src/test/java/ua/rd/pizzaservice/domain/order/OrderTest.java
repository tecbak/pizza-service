package ua.rd.pizzaservice.domain.order;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.discount.Discount;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.domain.pizza.Pizza;

import java.math.BigDecimal;
import java.util.HashMap;

import static java.math.BigDecimal.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {
    private Order order;
    @Mock
    private Pizza vegetarian;
    @Mock
    private Pizza meat;
    @Mock
    private Pizza sea;
    private HashMap<Pizza, Integer> pizzas;
    @Mock
    private Customer customer;
    @Mock
    private Discount discount;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        when(vegetarian.getPrice()).thenReturn(valueOf(60.0));
        when(meat.getPrice()).thenReturn(valueOf(80.0));
        when(sea.getPrice()).thenReturn(valueOf(100.0));

        pizzas = new HashMap<Pizza, Integer>() {{
            put(vegetarian, 2);
            put(meat, 2);
            put(sea, 1);
        }};
        order = new Order(customer, pizzas);
        when(discount.calculate(eq(order), any(BigDecimal.class))).thenReturn(valueOf(10.0));
    }

    @Test
    public void testGetPrice() throws Exception {
        BigDecimal expected = sea.getPrice(). //multiply(BigDecimal.valueOf(0.7)).
                add(vegetarian.getPrice().add(vegetarian.getPrice()).
                add(meat.getPrice()).add(meat.getPrice()));
        BigDecimal actual = order.getPrice();

        assertEquals(expected, actual);
    }

    @Test
    public void testSize() throws Exception {
        int expected = 5;
        int actual = order.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testApplyDiscountPrice() throws Exception {
        BigDecimal expected = valueOf(10.0);
        BigDecimal actual = order.applyDiscount(discount);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetDiscountedPrice() throws Exception {
        order.applyDiscount(discount);

        BigDecimal expected = valueOf(370.0);
        BigDecimal actual = order.getDiscountedPrice();

        assertEquals(expected, actual);
    }

    @Test
    public void testPay() throws Exception {
        order.pay();
        verify(customer).depositToLoyaltyCard(order.getDiscountedPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfPayInvokedAgain_throwException() throws Exception {
        order.pay();
        order.pay();
    }
}