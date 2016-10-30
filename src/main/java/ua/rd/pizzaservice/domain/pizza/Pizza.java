package ua.rd.pizzaservice.domain.pizza;

import org.springframework.context.annotation.Scope;
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
public class Pizza {

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
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Type type;


    /*Constructors*/

    public Pizza() {
    }

    public Pizza(Long id, String name, BigDecimal price, Type type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }


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