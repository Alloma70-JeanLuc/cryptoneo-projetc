package org.crypto.resource.books;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.db.entities.Book;
import org.crypto.db.repositories.BookRepository;

@RolesAllowed({"ADMIN","LIBRARIAN"})
@Path("/books")
public class BookManager extends CrudEndPointImpl<Book> {

    @Inject
    BookRepository bookRepository;

    @Inject
    public void initRepo() {
        setT(bookRepository);
    }
}
