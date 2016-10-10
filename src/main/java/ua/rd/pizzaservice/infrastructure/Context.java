package ua.rd.pizzaservice.infrastructure;

public interface Context {
    <T> T getBean(String beanName);
}
