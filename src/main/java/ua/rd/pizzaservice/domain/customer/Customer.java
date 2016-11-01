package ua.rd.pizzaservice.domain.customer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Component
@Scope(scopeName = "prototype")

@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
        @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "loyalty_card_balance", nullable = false)
    private BigDecimal loyaltyCardBalance = BigDecimal.ZERO;


    /*Getters and setters*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", loyaltyCardBalance=" + loyaltyCardBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        return loyaltyCardBalance != null ? loyaltyCardBalance.stripTrailingZeros().equals(customer.loyaltyCardBalance.stripTrailingZeros()) : customer.loyaltyCardBalance == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (loyaltyCardBalance != null ? loyaltyCardBalance.hashCode() : 0);
        return result;
    }
}
