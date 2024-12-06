package com.sakila;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.sakila.models.Actor;
import com.sakila.util.HibernateUtil;

public class TestHibernate {

    public static void main(String[] args) {
        // Inicia una sesión con Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Comienza una transacción
            transaction = session.beginTransaction();

            // Crear un nuevo Actor
            Actor newActor = new Actor();
            newActor.setFirstName("John");
            newActor.setLastName("Doe");

            // Guardar el Actor en la base de datos
            session.save(newActor);

            // Commit de la transacción
            transaction.commit();
            System.out.println("Actor guardado correctamente");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Si ocurre un error, hace rollback
            }
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
            HibernateUtil.shutdown();
        }
    }
}