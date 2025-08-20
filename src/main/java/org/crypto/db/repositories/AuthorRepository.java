package org.crypto.db.repositories;

import org.crypto.db.entities.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped // Indique que Quarkus doit gérer cette classe et qu'on pourra l'injecter ailleurs
public class AuthorRepository implements PanacheRepository<Author> {

    public List<Author> findByLastName(String lastName) {
        return list("lastName", lastName);
    }
}