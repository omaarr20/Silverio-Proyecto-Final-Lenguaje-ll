package com.sakila.models;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int id;

    @Column(name = "city")
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "last_update")
    private String lastUpdate;

    // Getters y setters
    // ...
}