package org.crypto.db.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.crypto.db.entities.Book;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {
    public Optional<Book> findByTitle(String title) {
        return find("title", title).firstResultOptional();
    }

    public Optional<Book> findByPublicationYear(int year) {
        return find("publicationYear", year).firstResultOptional();
    }

    public Optional<Book> findByIsbn(String isbn) {
        return find("isbn", isbn).firstResultOptional();
    }

}
