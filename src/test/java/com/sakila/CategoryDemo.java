package com.sakila;

import com.sakila.models.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CategoryDemo {

    public static void main(String[] args) {
        // Configuración de Hibernate
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") // Asegúrate de tener este archivo en tu proyecto
                .addAnnotatedClass(Category.class) // Añade la clase anotada
                .buildSessionFactory();

        Session session = null;

        try {
            // Abrir sesión y comenzar transacción
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            // Consulta todos los registros de la tabla Category
            List<Category> categories = session.createQuery("FROM Category", Category.class).list();

            // Mostrar resultados
            for (Category category : categories) {
                System.out.println("ID: " + category.getId());
                System.out.println("Name: " + category.getName());
                System.out.println("Last Update: " + category.getLastUpdate());
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
