package ru.prorain.repository;

import java.util.Collection;

public interface CrudRepository<T,K> {
    T save(T t);

    T update(T t);

    Collection<T> getAll();

    T getOne();

}
