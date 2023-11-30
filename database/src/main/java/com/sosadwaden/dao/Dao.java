package com.sosadwaden.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    Optional<E> findById(Long id);

    List<E> findAll();

    E save(E user);

    void update(E user);

    boolean delete(K id);
}
