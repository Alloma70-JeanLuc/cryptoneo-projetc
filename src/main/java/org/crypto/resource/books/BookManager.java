package org.crypto.resource.books;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.db.entities.Book;
import org.crypto.db.repositories.BookRepository;
import org.crypto.resource.books.dto.BookDTO;

import java.util.Optional;

@PermitAll
@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookManager extends CrudEndPointImpl<Book> {

    @Inject
    BookRepository repo;

    @Inject
    public void initRepo() {
        setT(repo);
    }

    @POST
    @Transactional
    public Response createBook(BookDTO bookDTO) {

        // 1. Vérifier si le livre existe déjà par titre
        if (repo.findByTitle(bookDTO.title) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Book already exists")
                    .build();
        }

        // 2. Créer et persister le livre
        Book book = new Book();
        book.title = bookDTO.title;
        book.isbn = bookDTO.isbn;
        book.description = bookDTO.description;
        book.publicationYear = bookDTO.publicationYear;
        book.code = bookDTO.code;        // si null, sera généré dans @PrePersist
        book.isActive = bookDTO.isActive;

        repo.persist(book);

        // 3. Créer un DTO minimal pour la réponse
        BookDTO response = new BookDTO();
        response.title = book.title;
        response.isbn = book.isbn;
        response.description = book.description;
        response.publicationYear = book.publicationYear;
        response.code = book.code;
        response.isActive = book.isActive;

        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    @Path("/isbn/{isbn}")
    public Response findBookByIsbn(@PathParam("isbn") String isbn) {
        Optional<Book> bookOpt = repo.findByIsbn(isbn);

        if (bookOpt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Aucun livre trouvé avec l'ISBN: " + isbn)
                    .build();
        }

        Book book = bookOpt.get();

        // Retourner un DTO minimal
        BookDTO response = new BookDTO();
        response.title = book.title;
        response.isbn = book.isbn;
        response.description = book.description;
        response.publicationYear = book.publicationYear;
        response.code = book.code;
        response.isActive = book.isActive;

        return Response.ok(response).build();
    }
}
