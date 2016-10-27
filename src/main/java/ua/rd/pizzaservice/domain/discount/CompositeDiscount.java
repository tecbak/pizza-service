package ua.rd.pizzaservice.domain.discount;

import ua.rd.pizzaservice.domain.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CompositeDiscount implements Discount {
    private final List<Discount> discounts = new ArrayList<>();

    public CompositeDiscount add(Discount discount) {
        if (discount == this) {
            throw new IllegalArgumentException("Composite discount can't contains itself");
        }
        discounts.add(discount);
        return this;
    }

    @Override
    public BigDecimal calculate(Order order, BigDecimal price) {
        BigDecimal totalDiscount = BigDecimal.ZERO;
        for (Discount discount : discounts) {
            BigDecimal currentPrice = price.subtract(totalDiscount);
            BigDecimal currentDiscount = discount.calculate(order, currentPrice);
            totalDiscount = totalDiscount.add(currentDiscount);
        }
        return totalDiscount;
    }
}
