package ua.rd.pizzaservice.domain;

import ua.rd.pizzaservice.domain.order.Status;
import ua.rd.pizzaservice.domain.pizza.Pizza;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Status status = Status.NEW;
    private List<Pizza> pizzas = new ArrayList<>();
    private Customer customer;

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public void addPizza(Pizza pizza) {

    }

    public void removePizza(Pizza pizza) {

    }

}

