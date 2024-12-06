package com.sakila.models;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private int id;

    @Column(name = "country")
    private String countryName;

    @Column(name = "last_update")
    private String lastUpdate;

    // Getters y setters
    // ...
}