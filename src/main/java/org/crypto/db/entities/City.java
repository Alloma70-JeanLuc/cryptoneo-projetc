package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Entity
@Table(name = "cities")
@Schema(description = "Représente une ville dans laquelle peuvent résider des auteurs ou être situées des bibliothèques.")
public class City extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de la ville (clé primaire).")
    public Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Nom unique de la ville.")
    public String name;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_active", nullable = false)
    @Schema(description = "Indique si la ville est active (true) ou désactivée (false).")
    public Boolean isActive = false;

    @Column(unique = true, nullable = false, length = 20)
    @Schema(description = "Code interne unique de la ville (ex: CITY-2025-001).")
    public String code;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "Date de création de la ville (timestamp).")
    public Instant createdAt;

    @Column(name = "deleted_at")
    @Schema(description = "Date de suppression logique de la ville, si applicable.")
    public Instant deletedAt;

    @Column(name = "updated_at")
    @Schema(description = "Date de dernière mise à jour des informations de la ville.")
    public Instant updatedAt;

    @Column(name = "deleted")
    @Schema(description = "Indique si la ville est supprimée logiquement (soft delete).")
    public Boolean deleted;

    // Génération automatique du code si non fourni
    @PrePersist
    public void generateCode() {
        if (this.code == null) {
            String prefix = "CITY";
            String timestamp = String.valueOf(System.currentTimeMillis()).substring(7); // suffixe court
            this.code = prefix + "-" + timestamp;
        }
    }
}
