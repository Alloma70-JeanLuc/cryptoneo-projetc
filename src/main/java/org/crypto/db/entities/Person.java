package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.Instant;

@MappedSuperclass // Indique que c'est une classe de base et que ses champs doivent être persistés dans les sous-classes.
public class Person extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String firstName;
    public String lastName;

    @Column(unique = true, nullable = false)
    public String email;

    public Instant createdAt = Instant.now();
    public Instant updatedAt;

    public boolean active = true;
    public boolean deleted = false;
    public Instant deletedAt;
}