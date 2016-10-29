package ua.rd.pizzaservice.repository;

interface Repository<T> {
    T find(Long id);

    T save(T t);
}
