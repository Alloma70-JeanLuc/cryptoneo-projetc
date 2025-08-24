package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Entity
@Table(name = "book_loans")
@Schema(description = "Représente un emprunt de livre effectué par un membre.")
public class BookLoan extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de l’emprunt (clé primaire).")
    public Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    @Schema(description = "Livre emprunté (relation ManyToOne vers Book).")
    public Book book;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_active", nullable = false)
    @Schema(description = "Indique si l’emprunt est actif (true) ou terminé/annulé (false).")
    public Boolean isActive = false;

    @Column(unique = true, nullable = false, length = 20)
    @Schema(description = "Code interne unique de l’emprunt (ex: LOAN-2025-001).")
    public String code;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "Date de création de l’emprunt (timestamp).")
    public Instant createdAt;

    @Column(name = "deleted_at")
    @Schema(description = "Date de suppression logique de l’emprunt, si applicable.")
    public Instant deletedAt;

    @Column(name = "updated_at")
    @Schema(description = "Date de dernière mise à jour de l’emprunt.")
    public Instant updatedAt;

    @Column(name = "deleted")
    @Schema(description = "Indique si l’emprunt est supprimé logiquement (soft delete).")
    public Boolean deleted;

    // Génération automatique du code si non fourni
    @PrePersist
    public void generateCode() {
        if (this.code == null) {
            String prefix = "LOAN";
            String timestamp = String.valueOf(System.currentTimeMillis()).substring(7); // suffixe court
            this.code = prefix + "-" + timestamp;
        }
    }
}
