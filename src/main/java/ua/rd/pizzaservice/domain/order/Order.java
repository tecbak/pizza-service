package ua.rd.pizzaservice.domain.order;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.discount.Discount;
import ua.rd.pizzaservice.domain.pizza.Pizza;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import static java.math.BigDecimal.*;
import static ua.rd.pizzaservice.domain.order.Status.*;

@Component
@Scope(scopeName = "prototype")

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o")
})
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pizzas_quantities", joinColumns = @JoinColumn(name = "order_id", nullable = false))
    @MapKeyJoinColumn(name = "pizza_id")
    @Column(name = "quantity", nullable = false)
    private Map<Pizza, Integer> pizzas;

    @Column(name = "discount_value", nullable = false)
    private BigDecimal discountValue = ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = NEW;

    /*Constructor*/

    public Order() {
    }

    public Order(Customer customer, Map<Pizza, Integer> pizzas) {
        this.customer = customer;
        this.pizzas = pizzas;
    }


    /*Getters and setters*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        checkChangeAvailable();
        this.customer = customer;
        discountValue = ZERO;
    }

    public Map<Pizza, Integer> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Map<Pizza, Integer> pizzas) {
        checkChangeAvailable();
        this.pizzas = pizzas;
        discountValue = ZERO;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }


    /*Methods*/

    public void pay() {
        setStatus(IN_PROGRESS);
        customer.depositToLoyaltyCard(getDiscountedPrice());
    }

    public void complete() {
        setStatus(DONE);
    }

    public void cancel() {
        setStatus(CANCELLED);
    }

    private void setStatus(Status newStatus) {
        checkAvailableChangeTo(newStatus);
        this.status = newStatus;
    }

    private void checkAvailableChangeTo(Status newStatus) {
        if (!status.isAvailableChangeTo(newStatus))
            throw new IllegalArgumentException("Can't change status from " + status + " to " + newStatus);
    }

    private void checkChangeAvailable() {
        if (status != NEW) {
            throw new IllegalStateException("It's possible to change only new order");
        }
    }

    public BigDecimal getPrice() {
        BigDecimal sum = ZERO;
        for (Map.Entry<Pizza, Integer> entry : pizzas.entrySet()) {
            sum = totalPriceOfOneTypeOfPizza(entry).add(sum);
        }
        return sum;
    }

    public BigDecimal applyDiscount(Discount discount) {
        checkChangeAvailable();
        discountValue = discount.calculate(this, getPrice());
        return discountValue;
    }

    public BigDecimal getDiscountedPrice() {
        return getPrice().subtract(discountValue);
    }

    private BigDecimal totalPriceOfOneTypeOfPizza(Map.Entry<Pizza, Integer> entry) {
        Pizza pizza = entry.getKey();

        BigDecimal price = pizza.getPrice();
        Integer quantity = entry.getValue();
        return price.multiply(valueOf(quantity));
    }

    public int size() {
        int size = 0;
        for (int quantity : pizzas.values()) {
            size += quantity;
        }
        return size;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", customer=").append(customer);
        sb.append(", pizzas=").append(pizzas);
        sb.append(", discountValue=").append(discountValue);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}