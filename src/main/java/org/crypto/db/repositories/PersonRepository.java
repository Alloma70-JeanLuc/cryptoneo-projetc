package org.crypto.db.repositories;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.crypto.db.entities.Person;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> { }

