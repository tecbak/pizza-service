package ua.rd.pizzaservice.domain.discount;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.Type;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuantityDiscountTest {
    private QuantityDiscount quantityDiscount;
    @Mock
    private Order order;
    private BigDecimal price;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        quantityDiscount = new QuantityDiscount();
        price = BigDecimal.TEN;
    }

    @Test
    public void testOnQuantityLessThen4_noDiscount() throws Exception {
        when(order.size()).thenReturn(3);

        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = quantityDiscount.calculate(order, price);

        assertEquals(expected, actual);
    }

    @Test
    public void testOnQuantity4AndMore_calculateDiscount() throws Exception {
        when(order.size()).thenReturn(6);
        when(order.getPizzas()).thenReturn(new HashMap<Pizza, Integer>() {{
            put(new Pizza(1L, "Vegetarian", BigDecimal.valueOf(60), Type.VEGETARIAN), 1);
            put(new Pizza(2L, "Meat", BigDecimal.valueOf(80), Type.MEAT), 2);
            put(new Pizza(3L, "Sea", BigDecimal.valueOf(100), Type.SEA), 3);
        }});


        BigDecimal expected = BigDecimal.valueOf(30.0);
        BigDecimal actual = quantityDiscount.calculate(order, price);
        assertEquals(expected, actual);
    }
}