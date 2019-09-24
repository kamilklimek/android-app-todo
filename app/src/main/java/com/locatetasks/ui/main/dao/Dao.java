package com.locatetasks.ui.main.dao;

import java.util.List;
import java.util.Optional;

interface Dao<T, S> {
    T save(T model);
    Optional<T> findById(S id);
    List<T> findAll();
    boolean remove(S id);
}
