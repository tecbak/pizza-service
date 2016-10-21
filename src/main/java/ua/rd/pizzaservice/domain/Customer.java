package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

public class Customer {
    private int id;
    private String name;
    private String address;
    private LoyaltyCard loyaltyCard = new LoyaltyCard();

    private static class LoyaltyCard {
        private BigDecimal amount = BigDecimal.ZERO;
        private BigDecimal withdrawLimit = BigDecimal.valueOf(0.10);

        private void deposit(BigDecimal sum) {
            amount = amount.add(sum);
        }

        private BigDecimal withdraw(BigDecimal sum) {
            if (amount.compareTo(sum) < 0) {
                amount = BigDecimal.ZERO;
                return amount;
            } else {
                amount = amount.subtract(sum);
                return sum;
            }
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
    public BigDecimal useLoyaltyCard(BigDecimal payment) {
        BigDecimal discount = loyaltyCard.withdraw(payment);
        BigDecimal paymentWithDiscount = payment.subtract(discount);
        return paymentWithDiscount;
    }


}
