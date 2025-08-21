package org.crypto.db.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.crypto.db.entities.Librarian;

@ApplicationScoped
public class LibrarianRepository implements PanacheRepository<Librarian> {
}
