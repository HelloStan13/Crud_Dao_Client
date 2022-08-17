package com.project1.DAO;

import java.util.List;
import java.util.Optional;

public interface ClientDAO<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
