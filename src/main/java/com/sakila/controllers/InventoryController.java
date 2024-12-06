package com.sakila.controllers;

import com.sakila.models.Inventory;
import com.sakila.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class InventoryController {

    // Método para obtener inventarios por `storeId`
    public List<Inventory> getInventoryByStore(int storeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Inventory WHERE storeId = :storeId";
            Query<Inventory> query = session.createQuery(hql, Inventory.class);
            query.setParameter("storeId", storeId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para obtener inventarios por `filmId`
    public List<Inventory> getInventoryByFilm(int filmId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Inventory WHERE filmId = :filmId";
            Query<Inventory> query = session.createQuery(hql, Inventory.class);
            query.setParameter("filmId", filmId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para agregar un nuevo inventario
    public void addInventory(Inventory inventory) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(inventory);
            transaction.commit();
            System.out.println("Nuevo inventario añadido: " + inventory);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para eliminar un inventario por `inventoryId`
    public void removeInventory(int inventoryId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Inventory inventory = session.get(Inventory.class, inventoryId);
            if (inventory != null) {
                session.delete(inventory);
                System.out.println("Inventario eliminado con ID: " + inventoryId);
            } else {
                System.out.println("Inventario con ID " + inventoryId + " no encontrado.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	throw new UnsupportedOperationException("Unimplemented method 'main'");
    }
}
