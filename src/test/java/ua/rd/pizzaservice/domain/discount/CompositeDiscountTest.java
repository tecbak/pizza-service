package ua.rd.pizzaservice.domain.discount;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.pizzaservice.domain.Order;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompositeDiscountTest {
    private CompositeDiscount discount = new CompositeDiscount();
    @Mock
    private Discount discount0;
    @Mock
    private Discount discount1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculate() throws Exception {
        when(discount0.calculate(any(Order.class), any(BigDecimal.class))).thenReturn(TEN);
        when(discount1.calculate(any(Order.class), any(BigDecimal.class))).thenReturn(ONE);

        BigDecimal expected = valueOf(11);
        BigDecimal actual = discount.add(discount0).add(discount1).calculate(new Order(), valueOf(100));

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOnAddItself_throwException() throws Exception {
        discount.add(discount);
    }
}