package com.sakila.controllers;

import com.sakila.models.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class FilmController {

    private SessionFactory sessionFactory;

    // Constructor para inicializar la fábrica de sesiones de Hibernate
    public FilmController() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Film.class).buildSessionFactory();
    }

    // Método para cerrar la fábrica de sesiones
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // Crear una nueva película
    public void createFilm(Film film) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(film);
            transaction.commit();
            System.out.println("Película creada exitosamente: " + film.getTitle());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Obtener todas las películas
    @SuppressWarnings("unchecked")
    public List<Film> getAllFilms() {
        Session session = sessionFactory.openSession();
        List<Film> films = null;
        try {
            films = session.createQuery("FROM Film").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return films;
    }

    // Obtener una película por ID
    public Film getFilmById(int id) {
        Session session = sessionFactory.openSession();
        Film film = null;
        try {
            film = session.get(Film.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return film;
    }

    // Actualizar una película
    public void updateFilm(int id, Film updatedFilm) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Film existingFilm = session.get(Film.class, id);
            if (existingFilm != null) {
                existingFilm.setTitle(updatedFilm.getTitle());
                existingFilm.setLanguage(updatedFilm.getLanguage());
                existingFilm.setCategories(updatedFilm.getCategories());
                existingFilm.setReleaseYear(updatedFilm.getReleaseYear());
                existingFilm.setLastUpdate(updatedFilm.getLastUpdate());
                session.update(existingFilm);
                transaction.commit();
                System.out.println("Película actualizada: " + existingFilm.getTitle());
            } else {
                System.out.println("Película con ID " + id + " no encontrada.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Eliminar una película
    public void deleteFilm(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Film film = session.get(Film.class, id);
            if (film != null) {
                session.delete(film);
                transaction.commit();
                System.out.println("Película eliminada: " + film.getTitle());
            } else {
                System.out.println("Película con ID " + id + " no encontrada.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	throw new UnsupportedOperationException("Unimplemented method 'main'");
    }
}
