package com.sakila.controllers;

import com.sakila.models.Customer;
import com.sakila.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerController {

    // Obtener todos los clientes
    public List<Customer> getAllCustomers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Customer";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Obtener un cliente por su ID
    public Customer getCustomerById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Crear un nuevo cliente
    public void addCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            System.out.println("Nuevo cliente añadido: " + customer);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Actualizar un cliente existente
    public void updateCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            System.out.println("Cliente actualizado: " + customer);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Eliminar un cliente por su ID
    public void deleteCustomer(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                session.delete(customer);
                System.out.println("Cliente eliminado con ID: " + id);
            } else {
                System.out.println("Cliente con ID " + id + " no encontrado.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Obtener clientes activos
    public List<Customer> getActiveCustomers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Customer WHERE active = true";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
