package com.sakila.data;

import java.util.List;

public interface IDatapost<T> {

    // Métodos estándar CRUD
    void create(T entity); // Post
    T read(int id); // Get por ID
    List<T> getAll(); // Get todos los datos
    void update(T entity); // Put
    void delete(int id); // Delete
}
