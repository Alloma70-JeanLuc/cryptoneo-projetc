package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Entity
@Table(name = "books")
@Schema(description = "Représente un livre dans le système.")
public class Book extends PanacheEntity {

    @Column(nullable = false)
    @Schema(description = "Titre du livre.")
    public String title;

    @Schema(description = "Numéro ISBN unique du livre.")
    public String isbn;

    @Schema(description = "Description ou résumé du livre.")
    public String description;

    @Schema(description = "Année de publication du livre.")
    public int publicationYear;

    @NotNull
    @Column(name = "active", nullable = false)
    @ColumnDefault("true")
    @Schema(description = "Indique si le livre est actif (true) ou inactif (false).")
    public Boolean isActive = true;

    @Column(unique = true, nullable = false, length = 20)
    @Schema(description = "Code interne unique du livre (ex: BOOK-2025-001).")
    public String code;

    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "Date de création du livre (timestamp).")
    public Instant createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Date de dernière mise à jour des informations du livre.")
    public Instant updatedAt;

    @Column(name = "deleted")
    @Schema(description = "Indique si le livre est supprimé logiquement (soft delete).")
    public Boolean deleted;

    @Column(name = "deleted_at")
    @Schema(description = "Date de suppression logique du livre, si applicable.")
    public Instant deletedAt;

    // Auto-génération du code et initialisation des valeurs avant persist
    @PrePersist
    public void prePersist() {
        if (this.code == null) {
            String prefix = "BOOK";
            String timestamp = String.valueOf(System.currentTimeMillis()).substring(7); // suffixe court
            this.code = prefix + "-" + timestamp;
        }
        if (this.createdAt == null) {
            this.createdAt = Instant.now();
        }
        if (this.updatedAt == null) {
            this.updatedAt = Instant.now();
        }
        if (this.isActive == null) {
            this.isActive = true;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
