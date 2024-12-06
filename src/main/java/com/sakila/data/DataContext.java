package com.sakila.data;

import java.util.ArrayList;
import java.util.List;

public abstract class DataContext<T> implements IDatapost<T> {

    // Simulación de una base de datos en memoria con una lista
    protected List<T> database = new ArrayList<>();

    @Override
    public final void create(T entity) {
        database.add(entity);
        System.out.println("Registro creado: " + entity);
    }

    @Override
    public final T read(int id) {
        // Simula la lectura de un objeto por ID
        if (id >= 0 && id < database.size()) {
            return database.get(id);
        } else {
            System.out.println("El ID proporcionado no es válido.");
            return null;
        }
    }

    @Override
    public final List<T> getAll() {
        return new ArrayList<>(database);
    }

    @Override
    public final void update(T entity) {
        // Aquí deberías usar un identificador único para encontrar y reemplazar el objeto.
        int index = database.indexOf(entity);
        if (index != -1) {
            database.set(index, entity);
            System.out.println("Registro actualizado: " + entity);
        } else {
            System.out.println("El registro no existe.");
        }
    }

    @Override
    public final void delete(int id) {
        if (id >= 0 && id < database.size()) {
            database.remove(id);
            System.out.println("Registro eliminado con ID: " + id);
        } else {
            System.out.println("El ID no es válido.");
        }
    }

    // Métodos concretos adicionales que los hijos no pueden sobrescribir
    public final List<T> search(String keyword) {
        // Implementación simplificada para buscar coincidencias en el toString() del objeto
        List<T> results = new ArrayList<>();
        for (T item : database) {
            if (item.toString().contains(keyword)) {
                results.add(item);
            }
        }
        return results;
    }
}

