package ua.rd.pizzaservice.domain.order;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.discount.Discount;
import ua.rd.pizzaservice.domain.pizza.Pizza;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.*;
import static ua.rd.pizzaservice.domain.order.Status.*;

@Component
@Scope(scopeName = "prototype")
public class Order {
    private Long id;
    private Customer customer;
    private Map<Pizza, Integer> pizzas = new HashMap<>();
    private Discount discount;
    //    private boolean paid;
    private Status status = NEW;

    /*Constructor*/
    public Order() {
    }

    public Order(Customer customer, List<Pizza> pizzas) {
        this.customer = customer;
        setPizzas(pizzas);
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

    public Map<Pizza, Integer> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        for (Pizza pizza : pizzas) {
            if (this.pizzas.containsKey(pizza)) {
                int quantity = this.pizzas.get(pizza);
                this.pizzas.put(pizza, quantity + 1);
            } else {
                this.pizzas.put(pizza, 1);
            }
        }
    }

    public Status getStatus() {
        return status;
    }

    private void setStatus(Status newStatus) {
        checkAvailableChangeTo(newStatus);
        this.status = newStatus;
    }

    private void checkAvailableChangeTo(Status newStatus) {
        if (!status.isAvailableChangeTo(newStatus))
            throw new IllegalArgumentException("Can't change newStatus from " + status + " to " + newStatus);
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /*Methods*/
    public void pay() {
//        if (paid) throw new IllegalStateException("Order is already paid");
        setStatus(IN_PROGRESS);
        customer.depositToLoyaltyCard(getDiscountedPrice());
//        paid = true;
    }

    public void complete() {
        setStatus(DONE);
    }

    public void cancel() {
        setStatus(CANCELLED);
    }

    public BigDecimal getPrice() {
        BigDecimal sum = ZERO;
        for (Map.Entry<Pizza, Integer> entry : pizzas.entrySet()) {
            sum = totalPriceOfOneTypeOfPizza(entry).add(sum);
        }
        return sum;
    }

    public BigDecimal getDiscountedPrice() {
        BigDecimal price = getPrice();
        BigDecimal discountAmount =
                discount == null ? ZERO : discount.calculate(this, price);
        return price.subtract(discountAmount);
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
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", pizzas=" + pizzas +
                '}';
    }
}