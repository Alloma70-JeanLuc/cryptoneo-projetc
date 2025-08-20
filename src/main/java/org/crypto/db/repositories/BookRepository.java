package org.crypto.db.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.crypto.db.entities.Book;

import java.util.List;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    public List<Book> findByTitle(String title) {
        return list("title", title);
    }

    public List<Book> findByPublicationYear(int year) {
        return list("publicationYear", year);
    }
}
