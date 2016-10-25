package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

public class Customer {
    private int id;
    private String name;
    private String address;
    private LoyaltyCard loyaltyCard = new LoyaltyCard();

    private static class LoyaltyCard {
        private static final BigDecimal discountRate = BigDecimal.valueOf(0.1);  //percent of amount
        private static final BigDecimal discountLimit = BigDecimal.valueOf(0.3); //percent of payment

        private BigDecimal amount = BigDecimal.ZERO;

        private void deposit(BigDecimal sum) {
            amount = amount.add(sum);
        }

        private BigDecimal calculateDiscount(BigDecimal payment) {
            BigDecimal discount = discountRate.multiply(amount);
            BigDecimal limit = discountLimit.multiply(payment);

            return discount.compareTo(limit) > 0 ? limit : discount;
        }
    }

    /*Getters and setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*Methods*/
    public BigDecimal getLoyaltyCardDiscount(BigDecimal payment) {
        return loyaltyCard.calculateDiscount(payment);
    }

    public void depositToLoyaltyCard(BigDecimal payment) {
        loyaltyCard.deposit(payment);
    }
}
