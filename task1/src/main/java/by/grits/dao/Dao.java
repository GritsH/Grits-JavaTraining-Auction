package by.grits.dao;

import java.util.List;

public interface Dao<T> {
    T getById(int id);

    void add(T t);

    void delete(T t);

    List<T> getAll();
}
