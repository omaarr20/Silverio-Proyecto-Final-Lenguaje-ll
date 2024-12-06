package com.sakila.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)  // Usar @Temporal para campos de fecha y hora
    private Date lastUpdate;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
