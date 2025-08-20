package org.crypto.resource.loans;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.db.entities.BookLoan;
import org.crypto.db.repositories.BookLoanRepository;

@Path("/loans")
public class BookLoanResource extends CrudEndPointImpl<BookLoan> {

    @Inject
    BookLoanRepository repo;

    @Inject
    public void initRepo() {
        setT(repo);
    }
}
