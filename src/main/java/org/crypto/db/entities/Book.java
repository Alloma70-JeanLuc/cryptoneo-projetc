package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "books")
public class Book extends PanacheEntity {

    @Column(nullable = false)
    public String title;

    public String isbn;
    public String description;
    public int publicationYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    public Author author;

    // Ajoutez d'autres relations ici (Genre, Publisher, etc.)

    public Instant createdAt = Instant.now();
    public Instant updatedAt;
    public boolean active = true;
    public boolean deleted = false;
    public Instant deletedAt;
}