package org.crypto.db.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.crypto.db.entities.BookLoan;

import java.util.List;

@ApplicationScoped
public class BookLoanRepository implements PanacheRepository<BookLoan> {

    public List<BookLoan> findByMember(Long memberId) {
        return list("member.id", memberId);
    }
}
