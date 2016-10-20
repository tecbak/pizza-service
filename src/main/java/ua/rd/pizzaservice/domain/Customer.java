package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

public class Customer {
    private int id;
    private String name;
    private String address;
    private LoyaltyCard loyaltyCard = new LoyaltyCard();

    private static class LoyaltyCard {
        private BigDecimal amount = BigDecimal.ZERO;

        private void deposit(BigDecimal sum) {
            amount = amount.add(sum);
        }

        private void withdraw(BigDecimal sum) {
            amount = amount.subtract(sum);
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

        return null; // TODO: 12-Oct-16 stub
    }
}
