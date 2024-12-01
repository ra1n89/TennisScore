package ru.prorain.repository;

import java.util.Collection;

interface CrudRepository<T,K> {
    T save(T id);

    T update(T id);

    Collection<T> getAll();

    T getOne();

}
