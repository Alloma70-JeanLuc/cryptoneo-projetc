package org.crypto.resource.authors;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.core.features.ApiResponse;
import org.crypto.db.entities.Author;
import org.crypto.db.repositories.AuthorRepository;


@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorManager extends CrudEndPointImpl<Author> {

    @Inject
    AuthorRepository authorRepository;

    @PostConstruct
    public void init() {
        this.setT(authorRepository);
    }

}
