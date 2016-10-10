package ua.rd.pizzaservice.domain;

public enum Type {
    VEGETARIAN(1L),
    SEA(2L),
    MEAT(3L);

    private Long pizzaID;

    Type(Long pizzaID) {
        this.pizzaID = pizzaID;
    }

    public Long getPizzaID() {
        return pizzaID;
    }
}
