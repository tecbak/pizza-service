package ua.rd.pizzaservice.repository;

import java.util.List;

interface Repository<T> {
    T find(Long id);

    List<T> findAll();

    T save(T t);
}
