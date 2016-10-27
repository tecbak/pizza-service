package ua.rd.pizzaservice.domain.discount;

import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.customer.Customer;

import java.math.BigDecimal;

public class LoyaltyCardDiscount implements Discount {
    private BigDecimal discountRate;  // = BigDecimal.valueOf(0.1);  //percent of amount
    private BigDecimal discountLimit; // = BigDecimal.valueOf(0.3); //percent of payment

    /*Constructors*/
    public LoyaltyCardDiscount() {
    }

    public LoyaltyCardDiscount(BigDecimal discountRate, BigDecimal discountLimit) {
        this.discountRate = discountRate;
        this.discountLimit = discountLimit;
    }

    /*Getters and setters*/
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountLimit() {
        return discountLimit;
    }

    public void setDiscountLimit(BigDecimal discountLimit) {
        this.discountLimit = discountLimit;
    }

    /*Methods*/
    @Override
    public BigDecimal calculate(Order order, BigDecimal price) {
        BigDecimal balance = order.getCustomer().getLoyaltyCardBalance();
        BigDecimal discount = discountRate.multiply(balance);
        BigDecimal limit = discountLimit.multiply(price);

        return discount.compareTo(limit) > 0 ? limit : discount;
    }
}
