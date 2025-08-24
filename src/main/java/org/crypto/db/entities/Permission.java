package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Entity
@Table(name = "permissions")
@Schema(description = "Représente une permission du système, utilisée pour contrôler l’accès aux fonctionnalités.")
public class Permission extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de la permission (clé primaire).")
    public Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Nom unique de la permission (ex: CREATE_BOOK, DELETE_AUTHOR).")
    public String name;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_active", nullable = false)
    @Schema(description = "Indique si la permission est active (true) ou désactivée (false).")
    public Boolean isActive = true;

    @Column(unique = true, nullable = false, length = 20)
    @Schema(description = "Code interne unique de la permission (ex: PERM-2025-001).")
    public String code;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "Date de création de la permission (timestamp).")
    public Instant createdAt = Instant.now();

    @Column(name = "deleted_at")
    @Schema(description = "Date de suppression logique de la permission, si applicable.")
    public Instant deletedAt;

    @Column(name = "updated_at")
    @Schema(description = "Date de dernière mise à jour de la permission.")
    public Instant updatedAt;

    @Column(name = "deleted", nullable = false)
    @Schema(description = "Indique si la permission est supprimée logiquement (soft delete).")
    public Boolean deleted = false;

    // Génération automatique du code si non fourni
    @PrePersist
    public void generateCode() {
        if (this.code == null) {
            String prefix = "PERM";
            String timestamp = String.valueOf(System.currentTimeMillis()).substring(7); // suffixe court
            this.code = prefix + "-" + timestamp;
        }
    }
}
