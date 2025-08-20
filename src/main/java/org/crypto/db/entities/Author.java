package org.crypto.db.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author extends Person {

    @ManyToOne
    @JoinColumn(name = "city_id") // clé étrangère vers City
    public City city;
    public String biography;
}
