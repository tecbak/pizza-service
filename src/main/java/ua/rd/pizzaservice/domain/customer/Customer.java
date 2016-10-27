package ua.rd.pizzaservice.domain.customer;

import java.math.BigDecimal;

public class Customer {
    private int id;
    private String name;
    private String address;
    private BigDecimal loyaltyCardBalance = BigDecimal.ZERO;
//    private LoyaltyCard loyaltyCard = new LoyaltyCard();

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
    public BigDecimal getLoyaltyCardBalance() {
        return loyaltyCardBalance;
    }

    public void depositToLoyaltyCard(BigDecimal payment) {
        loyaltyCardBalance = loyaltyCardBalance.add(payment);
    }


//        public BigDecimal getLoyaltyCardDiscount (BigDecimal payment){
//            return loyaltyCard.calculateDiscount(payment);
//        }
//
//    public void depositToLoyaltyCard(BigDecimal payment) {
//        loyaltyCard.deposit(payment);
//    }
}
