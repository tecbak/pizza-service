package ua.rd.pizzaservice.domain.discount;

import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.domain.pizza.Pizza;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;

public class QuantityDiscount  implements Discount {
    private int minQuantity; // = 4;
    private BigDecimal discountRate; // = valueOf(0.3);

    /*Constructors*/
    public QuantityDiscount() {
    }

    public QuantityDiscount(int minQuantity, BigDecimal discountRate) {
        this.minQuantity = minQuantity;
        this.discountRate = discountRate;
    }

    /*Getters and setters*/
    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    /*Methods*/
    @Override
    public BigDecimal calculate(Order order, BigDecimal price) {
        if (order.size() < minQuantity) {
            return ZERO;
        } else {
            return maxPrice(order).multiply(discountRate);
        }
    }

    private BigDecimal maxPrice(Order order) {
        BigDecimal max = ZERO;
        for (Pizza pizza : order.getPizzas().keySet()) {
            if (pizza.getPrice().compareTo(max) > 0) {
                max = pizza.getPrice();
            }
        }
        return max;
    }
}
