package org.crypto.db.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.crypto.db.entities.City;

@ApplicationScoped
public class CityRepository implements PanacheRepository<City> {

    public City findByName(String name) {
        return find("name", name).firstResult();
    }
}

