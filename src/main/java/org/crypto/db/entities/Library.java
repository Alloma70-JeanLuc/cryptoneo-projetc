package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "libraries")
public class Library extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String address;

    @ManyToOne
    public City city;

    public Library(){}

    public Library(String name, String adds, City city){
        this.address = adds;
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(City city) {
        this.city = city;
    }
}

