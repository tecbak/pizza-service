package ua.rd.pizzaservice.domain.customer;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "adress_id")
    private Address address;

    @Column(name = "loyalty_card_balance", nullable = false)
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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
