package ua.rd.pizzaservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Customer{
    @Id
    private int id;
    private String name;
    private String address;
    private BigDecimal loyaltyCardBalance = BigDecimal.ZERO;

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
}
