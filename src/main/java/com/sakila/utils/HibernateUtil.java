package com.sakila.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Crear SessionFactory usando hibernate.cfg.xml
            sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") // Carga la configuración desde el archivo
                .addAnnotatedClass(com.sakila.models.Actor.class)
                .addAnnotatedClass(com.sakila.models.Address.class)
                .addAnnotatedClass(com.sakila.models.Category.class)
                .addAnnotatedClass(com.sakila.models.City.class)
                .addAnnotatedClass(com.sakila.models.Country.class)
                .addAnnotatedClass(com.sakila.models.Customer.class)
                .addAnnotatedClass(com.sakila.models.Film.class)
                .addAnnotatedClass(com.sakila.models.FilmActor.class) // Verifica que estas clases existan
                .addAnnotatedClass(com.sakila.models.FilmCategory.class)
                .addAnnotatedClass(com.sakila.models.Inventory.class)
                .addAnnotatedClass(com.sakila.models.Language.class)
                .addAnnotatedClass(com.sakila.models.Payment.class)
                .addAnnotatedClass(com.sakila.models.Rental.class)
                .addAnnotatedClass(com.sakila.models.Staff.class)
                .addAnnotatedClass(com.sakila.models.Store.class)
                .buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Fallo al crear la SessionFactory: " + e.getMessage());
        }
    }

    // Método para obtener la SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Método para cerrar la SessionFactory
    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // Método para obtener una sesión de Hibernate
    public static Session getSession() {
        return sessionFactory.getCurrentSession(); // Obtener una sesión del contexto actual
    }
}
