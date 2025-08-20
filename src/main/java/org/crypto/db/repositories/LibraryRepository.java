package org.crypto.db.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.crypto.db.entities.Library;

@ApplicationScoped
public class LibraryRepository implements PanacheRepository<Library> {
    public Library findByName(String name) {
        return find("name", name).firstResult();
    }
}
