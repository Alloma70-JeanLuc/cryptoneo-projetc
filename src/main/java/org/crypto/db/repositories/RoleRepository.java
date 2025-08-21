package org.crypto.db.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.crypto.db.entities.Role;

@ApplicationScoped
public class RoleRepository implements PanacheRepository<Role> {

    public Role findByName(String name) {
        return find("LOWER(TRIM(name)) = ?1", name.trim().toLowerCase())
                .firstResult();
    }
}
