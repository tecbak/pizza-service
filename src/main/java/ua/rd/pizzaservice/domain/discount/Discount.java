package ua.rd.pizzaservice.domain.discount;

import ua.rd.pizzaservice.domain.Order;

import java.math.BigDecimal;

public interface Discount {
    BigDecimal calculate(Order order, BigDecimal price);
}
