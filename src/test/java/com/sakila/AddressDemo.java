package com.sakila;

import com.sakila.models.Address;
import com.sakila.models.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AddressDemo {

    public static void main(String[] args) {
        // Configuración de Hibernate
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") // Asegúrate de tener este archivo en tu proyecto
                .addAnnotatedClass(Address.class) // Añade la clase anotada
                .addAnnotatedClass(City.class) // Asegúrate de añadir la clase City
                .buildSessionFactory();

        Session session = null;

        try {
            // Abrir sesión y comenzar transacción
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            // Consulta todos los registros de la tabla Address
            List<Address> addresses = session.createQuery("FROM Address", Address.class).list();

            // Mostrar resultados
            for (Address address : addresses) {
                System.out.println("ID: " + address.getId());
                System.out.println("Address: " + address.getAddress());
                System.out.println("Address 2: " + address.getAddress2());
                System.out.println("District: " + address.getDistrict());
                System.out.println("City: " + (address.getCity() != null ? address.getCity().getClass() : "No City"));
                System.out.println("Postal Code: " + address.getPostalCode());
                System.out.println("Phone: " + address.getPhone());
                System.out.println("Last Update: " + address.getLastUpdate());
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
