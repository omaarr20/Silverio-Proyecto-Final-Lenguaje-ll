package com.sakila.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "film_category")
public class FilmCategory implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2837164513404536355L;

    @Id
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "last_update")
    private String lastUpdate;

    // Getters y Setters
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
