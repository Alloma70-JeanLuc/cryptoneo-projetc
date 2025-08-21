package org.crypto.resource.books;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.db.entities.Book;
import org.crypto.db.repositories.BookRepository;

import java.util.Optional;

@RolesAllowed({"ADMIN","LIBRARIAN", "AUTHOR"})
@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookManager extends CrudEndPointImpl<Book> {

    @Inject
    BookRepository bookRepository;

    @Inject
    public void initRepo() {
        setT(bookRepository);
    }

    @Transactional
    @POST
    @Path("create")
    public Response createBook(Book book) {
        bookRepository.persist(book);
        return Response.ok(book).build();
    }

    //Trouner un Book en fonction de son isbn
    @Transactional
    @GET
    @Path("/isbn/{isbn}")
    public Response findBookByIsbn(@PathParam("isbn") String isbn){
        Optional<Book> b = bookRepository.findByIsbn(isbn);
        if(isbn == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Le livre est introuvable avec l'insb: "+isbn)
                    .build();
        }
        return Response.ok(b)
                .build();
     }


}
