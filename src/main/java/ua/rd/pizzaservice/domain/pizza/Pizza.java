package ua.rd.pizzaservice.domain.pizza;

import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Component
@Scope(scopeName = "prototype")

@Entity
@Table(name = "pizzas")
@NamedQueries({
        @NamedQuery(name = "Pizza.findAll", query = "SELECT p FROM Pizza p")
})
public class Pizza extends ResourceSupport{

//    @TableGenerator(name = "Pizza_Gen",
//            table = "ID_GEN",
//            pkColumnName = "Gen_name",
//            valueColumnName = "Gen_val",
//            initialValue = 0,
//            allocationSize = 50) //analog - sequence
//    @Id @GeneratedValue(generator = "Pizza_Gen")

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pizzaId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Type type;


    /*Constructors*/

    public Pizza() {
    }

    public Pizza(Long pizzaId, String name, BigDecimal price, Type type) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.price = price;
        this.type = type;
    }


    /*Getters and setters*/

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long id) {
        this.pizzaId = id;
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
                "id=" + pizzaId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (name != null ? !name.equals(pizza.name) : pizza.name != null) return false;
        if (price != null ? !price.equals(pizza.price) : pizza.price != null) return false;
        return type == pizza.type;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}