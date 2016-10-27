package ua.rd.pizzaservice.domain.discount;

import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;

import java.math.BigDecimal;

public class QuantityDiscount implements Discount {
    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.3);

    @Override
    public BigDecimal calculate(Order order, BigDecimal price) {
        if (order.size() < 4) {
            return BigDecimal.ZERO;
        } else {
            return maxPrice(order).multiply(DISCOUNT_RATE);
        }
    }

    private BigDecimal maxPrice(Order order) {
        BigDecimal max = BigDecimal.ZERO;
        for (Pizza pizza : order.getPizzas().keySet()) {
            if (pizza.getPrice().compareTo(max) > 0) {
                max = pizza.getPrice();
            }
        }
        return max;
    }
}
