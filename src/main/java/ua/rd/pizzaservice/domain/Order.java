package ua.rd.pizzaservice.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Scope(scopeName = "prototype")
public class Order {
    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.3);
    private Long id;
    private Customer customer;
    private List<Pizza> pizzas;
    private Statuses status = Statuses.NEW;

    /*Constructor*/
    public Order() {
    }

    public Order(Customer customer, List<Pizza> pizzas) {
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
        this.customer = customer;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Statuses getStatus() {
        return status;
    }

    private void setStatus(Statuses status) {
        if (!this.status.isAvailableChange(status))
            throw new IllegalArgumentException("Can't change status from " + this.status + " to " + status);

        this.status = status;
    }

    /*Methods*/
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", pizzas=" + pizzas +
                '}';
    }

    public BigDecimal getPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Pizza pizza : pizzas) {
            sum = sum.add(pizza.getPrice());
        }
        return sum;
    }

    public BigDecimal getDiscount() {
        if (pizzas.size() < 4) {
            return BigDecimal.ZERO;
        } else {
            return calculateDiscount();
        }
    }

    private BigDecimal calculateDiscount() {
        return maxPrice().multiply(DISCOUNT_RATE);
    }

    private BigDecimal maxPrice() {
        BigDecimal max = BigDecimal.ZERO;

        for (Pizza pizza : pizzas) {
            if (pizza.getPrice().compareTo(max) > 0) {
                max = pizza.getPrice();
            }
        }

        return max;
    }

}
