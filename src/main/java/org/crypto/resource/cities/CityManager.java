package org.crypto.resource.cities;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.db.entities.City;
import org.crypto.db.repositories.CityRepository;

@Path("/cities")
public class CityManager extends CrudEndPointImpl<City> {

    @Inject
    CityRepository repo;

    @Inject
    public void initRepo() {
        setT(repo);
    }
}
