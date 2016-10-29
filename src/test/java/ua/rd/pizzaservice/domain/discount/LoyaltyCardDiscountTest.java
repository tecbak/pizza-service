package ua.rd.pizzaservice.domain.discount;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.domain.customer.Customer;

import java.math.BigDecimal;
import java.util.HashMap;

import static java.math.BigDecimal.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoyaltyCardDiscountTest {
    private LoyaltyCardDiscount discount;
    private Order order;
    @Mock
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        discount = new LoyaltyCardDiscount(valueOf(0.1), valueOf(0.3));
        order = new Order(customer, new HashMap<>());
    }

    @Test
    public void testDiscountBelowLimit() throws Exception {
        when(customer.getLoyaltyCardBalance()).thenReturn(valueOf(100));

        BigDecimal expected = valueOf(10.0);
        BigDecimal actual = discount.calculate(order, valueOf(100));

        assertEquals(expected, actual);
    }

    @Test
    public void testDiscountAboveLimit() throws Exception {
        when(customer.getLoyaltyCardBalance()).thenReturn(valueOf(1000));

        BigDecimal expected = valueOf(30.0);
        BigDecimal actual = discount.calculate(order, valueOf(100));

        assertEquals(expected, actual);
    }


}