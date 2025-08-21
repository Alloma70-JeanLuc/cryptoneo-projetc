package org.crypto.db.entities;


import jakarta.persistence.*;

@Entity
@DiscriminatorValue("AUTHOR")
public class Author extends Person {

    @ManyToOne
    @JoinColumn(name = "city_id") // clé étrangère vers City
    public City city;
    public String biography;
}
