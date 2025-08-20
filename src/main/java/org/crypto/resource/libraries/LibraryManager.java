package org.crypto.resource.libraries;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.db.entities.Library;
import org.crypto.db.repositories.LibraryRepository;

@Path("/libraries")
public class LibraryManager extends CrudEndPointImpl<Library> {

    @Inject
    LibraryRepository repo;

    @Inject
    public void initRepo() {
        setT(repo);
    }
}
