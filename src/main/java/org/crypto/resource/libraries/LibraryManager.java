package org.crypto.resource.libraries;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.db.entities.Library;
import org.crypto.db.repositories.LibraryRepository;

@Path("/libraries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LibraryManager extends CrudEndPointImpl<Library> {

    @Inject
    LibraryRepository repo;

    @Inject
    public void initRepo() {
        setT(repo);
    }


    @POST
    @Transactional
    public Response createLibrary(Library lib){
        repo.persist(lib);
        return Response.ok(lib)
                .build();
    }



}
