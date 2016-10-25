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

        private void withdraw(BigDecimal sum) {
            amount = amount.subtract(sum);
        }

        private BigDecimal calculateDiscount(BigDecimal payment) {
            BigDecimal discount = discountRate.multiply(payment);
            BigDecimal limit = discountLimit.multiply(payment);

            return discount.compareTo(limit) > 0 ? limit : discount;
//            if (discount.compareTo(limit) > 0) {
//                discount = limit;
//            }
//
//            return discount;
        }

//        @Deprecated
//        public BigDecimal depositAndGetDiscount(BigDecimal payment) {
//            BigDecimal discount = discountRate.multiply(payment);
//            BigDecimal limit = discountLimit.multiply(payment);
//
//            if (discount.compareTo(limit) > 0) {
//                discount = limit;
//            }
//            amount = amount.add(payment).subtract(discount);
//            return discount;
//        }
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
    public BigDecimal calculateLoyaltyCardDiscount(BigDecimal payment) {
        return loyaltyCard.calculateDiscount(payment);
    }

    public void withdrawFromLoyaltyCard(BigDecimal payment) {
        loyaltyCard.withdraw(payment);
    }

    public void depositToLoyaltyCard(BigDecimal payment) {
        loyaltyCard.deposit(payment);
    }
//    public BigDecimal depositAndGetLoyaltyCardDiscount(BigDecimal payment) {
//        return loyaltyCard.depositAndGetDiscount(payment);
//    }
}
