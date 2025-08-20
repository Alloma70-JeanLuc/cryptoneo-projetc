package org.crypto.db.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.crypto.db.entities.Account;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {

    public Account findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
