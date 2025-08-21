package org.crypto.resource.loans;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.core.features.ApiResponse;
import org.crypto.db.entities.BookLoan;
import org.crypto.db.entities.Librarian;
import org.crypto.db.repositories.BookLoanRepository;

@RolesAllowed({"ADMIN","LIBRARIAN", "MEMBER"})
@Path("/loans")
public class BookLoanManager extends CrudEndPointImpl<BookLoan> {

    @Inject
    BookLoanRepository repo;

    @Inject
    public void initRepo() {
        setT(repo);
    }

    @POST
    @Transactional
    public Response createLoan(BookLoan bl){
        repo.persist(bl);
        return Response.ok(bl)
                .build();
    }
}
