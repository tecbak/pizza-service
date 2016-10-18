package ua.rd.pizzaservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Pizza {
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
    private Type type;

    public Pizza(Long id, String name, BigDecimal price, Type type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Pizza() {
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
