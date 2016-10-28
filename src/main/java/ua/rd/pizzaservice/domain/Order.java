package ua.rd.pizzaservice.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.rd.pizzaservice.domain.discount.Discount;
import ua.rd.pizzaservice.domain.pizza.Pizza;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.*;

@Component
@Scope(scopeName = "prototype")
public class Order {
//    private static final BigDecimal DISCOUNT_RATE = valueOf(0.3);

    private Long id;
    private Customer customer;
    private Map<Pizza, Integer> pizzas = new HashMap<>();
    private Discount discount;
    private boolean paid;
    private Statuses status = Statuses.NEW;

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

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        checkAvailableChange(status);
        this.status = status;
    }

    private void checkAvailableChange(Statuses status) {
        if (!this.status.isAvailableChange(status))
            throw new IllegalArgumentException("Can't change status from " + this.status + " to " + status);
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /*Methods*/
//    public BigDecimal getPriceWithTotalDiscount() {
//        return getPrice().subtract(getTotalDiscount());
//    }

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

//    public BigDecimal getTotalDiscount() {
//        return getQuantityDiscount().add(getLoyaltyCardDiscount());
//    }

//    public BigDecimal getQuantityDiscount() {
//        if (size() < 4) {
//            return BigDecimal.ZERO;
//        } else {
//            return maxPrice().multiply(DISCOUNT_RATE);
//        }
//    }

//    public BigDecimal getLoyaltyCardDiscount() {
//        BigDecimal priceWithQuantityDiscount = getPrice().subtract(getQuantityDiscount());
//        return customer.getLoyaltyCardDiscount(priceWithQuantityDiscount);
//    }

    public void pay() {
        if (paid) throw new IllegalStateException("Order is already paid");
        customer.depositToLoyaltyCard(getDiscountedPrice());
        paid = true;
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

//    private BigDecimal maxPrice() {
//        BigDecimal max = ZERO;
//        for (Pizza pizza : pizzas.keySet()) {
//            if (pizza.getPrice().compareTo(max) > 0) {
//                max = pizza.getPrice();
//            }
//        }
//        return max;
//    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", pizzas=" + pizzas +
                '}';
    }
}
