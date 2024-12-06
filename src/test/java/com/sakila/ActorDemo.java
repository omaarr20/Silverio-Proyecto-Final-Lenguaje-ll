package com.sakila;

import com.sakila.models.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ActorDemo {

    public static void main(String[] args) {
        // Configuración de Hibernate
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") // Asegúrate de tener este archivo en tu proyecto
                .addAnnotatedClass(Actor.class) // Añade la clase anotada
                .buildSessionFactory();

        Session session = null;

        try {
            // Abrir sesión y comenzar transacción
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            // Consulta todos los actores de la tabla
            List<Actor> actors = session.createQuery("FROM Actor", Actor.class).list();

            // Mostrar resultados
            for (Actor actor : actors) {
                System.out.println("ID: " + actor.getId());
                System.out.println("First Name: " + actor.getFirstName());
                System.out.println("Last Name: " + actor.getLastName());
                System.out.println("Last Update: " + actor.getLastUpdate());
                System.out.println("====================================");
            }

            // Confirmar transacción
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            sessionFactory.close();
        }
    }
}
