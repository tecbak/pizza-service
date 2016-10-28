package ua.rd.pizzaservice.domain.discount;

import ua.rd.pizzaservice.domain.order.Order;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
@Entity
public interface Discount {
    @Id
    Long getId();
    void setId(Long id);
    BigDecimal calculate(Order order, BigDecimal price);
}
