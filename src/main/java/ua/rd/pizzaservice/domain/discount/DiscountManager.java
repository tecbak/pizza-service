package ua.rd.pizzaservice.domain.discount;

import ua.rd.pizzaservice.domain.Order;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class DiscountManager {
    private final List<Discount> discounts;

    public DiscountManager(Discount... discounts) {
        this.discounts = Arrays.asList(discounts);
    }

    public BigDecimal getPriceWithDiscounts(Order order) {
        BigDecimal price = order.getPrice();
        for (Discount discount : discounts) {
            BigDecimal discountValue = discount.calculate(order, price);
            price = price.subtract(discountValue);
        }
        return price;
    }
}
