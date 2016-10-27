package ua.rd.pizzaservice.domain.customer;

import java.math.BigDecimal;

class LoyaltyCard {
    private static final BigDecimal discountRate = BigDecimal.valueOf(0.1);  //percent of amount
    private static final BigDecimal discountLimit = BigDecimal.valueOf(0.3); //percent of payment

    private BigDecimal amount = BigDecimal.ZERO;

    void deposit(BigDecimal sum) {
        amount = amount.add(sum);
    }

    BigDecimal calculateDiscount(BigDecimal payment) {
        BigDecimal discount = discountRate.multiply(amount);
        BigDecimal limit = discountLimit.multiply(payment);

        return discount.compareTo(limit) > 0 ? limit : discount;
    }
}