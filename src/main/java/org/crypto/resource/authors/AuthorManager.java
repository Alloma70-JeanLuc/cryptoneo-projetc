package org.crypto.resource.authors;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.db.entities.Author;
import org.crypto.db.repositories.AuthorRepository;

@Path("/authors")
public class AuthorManager extends CrudEndPointImpl<Author> {

    @Inject
    AuthorRepository authorRepository;

    @Inject
    public void initRepo() {
        // Important : on lie ton repository concret à CrudEndPointImpl
        setT(authorRepository);
    }
}
