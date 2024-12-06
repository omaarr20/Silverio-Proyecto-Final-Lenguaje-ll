package com.sakila.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "film_actor")
public class FilmActor implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4599199243278144855L;

    @Id
    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Id
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "last_update")
    private String lastUpdate;

    // Getters y Setters
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
