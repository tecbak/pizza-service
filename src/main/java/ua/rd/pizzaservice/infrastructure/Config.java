package ua.rd.pizzaservice.infrastructure;

public interface Config {
    Class<?> getImpl(String name);
}
