package org.crypto.db.repositories;

import jakarta.ws.rs.core.Response;
import org.crypto.db.entities.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class AuthorRepository implements PanacheRepository<Author> {

    public List<Author> findByLastName(String lastName) {

        return list("lastName", lastName);
    }


    public Response create(Object entry) throws Exception {
        throw new Exception("Not yet");
    }

}