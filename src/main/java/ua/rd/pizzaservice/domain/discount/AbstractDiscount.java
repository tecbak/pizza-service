package ua.rd.pizzaservice.domain.discount;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class AbstractDiscount implements Discount {
    @Id
    private Long id;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
