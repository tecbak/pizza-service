package ua.rd.pizzaservice.domain.order;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.discount.Discount;
import ua.rd.pizzaservice.domain.pizza.Pizza;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.*;
import static ua.rd.pizzaservice.domain.order.Status.*;

@Component
@Scope(scopeName = "prototype")
@Entity
@Table(name = "orders")
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
    private Map<Pizza, Integer> pizzas; //= new HashMap<>();

//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "discount_id")
//    private Discount discount;

    private BigDecimal discountValue = ZERO;

    @Enumerated(EnumType.STRING)
    private Status status = NEW;

    /*Constructor*/
    public Order() {
    }

    public Order(Customer customer, Map<Pizza, Integer> pizzas) {
        this.customer = customer;
        this.pizzas = pizzas;
//        setPizzas(pizzas);
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
//        List<Pizza> pizzas = new ArrayList<>();
//        for (Map.Entry<Pizza, Integer> pizzaEntry : this.pizzas.entrySet()) {
//            for (int i = 0, n = pizzaEntry.getValue(); i < n; i++) {
//                pizzas.add(pizzaEntry.getKey());
//            }
//        }
        return pizzas;
    }

    public void setPizzas(Map<Pizza, Integer> pizzas) {
        this.pizzas = pizzas;
//        for (Pizza pizza : pizzas) {
//            if (this.pizzas.containsKey(pizza)) {
//                int quantity = this.pizzas.get(pizza);
//                this.pizzas.put(pizza, quantity + 1);
//            } else {
//                this.pizzas.put(pizza, 1);
//            }
//        }
    }

    public Status getStatus() {
        return status;
    }

//    public Discount getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(Discount discount) {
//        this.discount = discount;
//    }

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

    private void setStatus(Status newStatus) {
        checkAvailableChangeTo(newStatus);
        this.status = newStatus;
    }

    private void checkAvailableChangeTo(Status newStatus) {
        if (!status.isAvailableChangeTo(newStatus))
            throw new IllegalArgumentException("Can't change newStatus from " + status + " to " + newStatus);
    }

    public BigDecimal getPrice() {
        BigDecimal sum = ZERO;
        for (Map.Entry<Pizza, Integer> entry : pizzas.entrySet()) {
            sum = totalPriceOfOneTypeOfPizza(entry).add(sum);
        }
        return sum;
    }

    public BigDecimal applyDiscount(Discount discount) {
        discountValue = discount.calculate(this, getPrice());
        return discountValue;
    }

    public BigDecimal getDiscountedPrice() {
//        BigDecimal price = getPrice();
//        BigDecimal discountAmount =
//                discount == null ? ZERO : discount.calculate(this, price);
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
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", pizzas=" + pizzas +
                '}';
    }
}