package com.sakila.controllers;

import com.sakila.models.Rental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;
import java.util.List;

public class RentalController {

    private SessionFactory sessionFactory;

    public RentalController() {
        // Configuración de Hibernate
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") // Asegúrate de tener el archivo de configuración
                .addAnnotatedClass(Rental.class)
                .buildSessionFactory();
    }

    // Método para crear un nuevo Rental
    public void createRental(Rental rental) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(rental);
            transaction.commit();
            System.out.println("Rental creado con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para obtener todos los Rentals
    public List<Rental> getAllRentals() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Rental", Rental.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para obtener un Rental por ID
    public Rental getRentalById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Rental.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para actualizar un Rental
    public void updateRental(Rental rental) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(rental);
            transaction.commit();
            System.out.println("Rental actualizado con éxito.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para eliminar un Rental por ID
    public void deleteRentalById(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Rental rental = session.get(Rental.class, id);
            if (rental != null) {
                session.delete(rental);
                System.out.println("Rental eliminado con éxito.");
            } else {
                System.out.println("Rental no encontrado.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para cerrar la sesión de Hibernate
    public void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	throw new UnsupportedOperationException("Unimplemented method 'main'");
    }
}
