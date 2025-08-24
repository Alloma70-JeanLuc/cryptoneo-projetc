package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "roles")
@Schema(description = "Représente un rôle dans le système, attribué aux comptes et lié aux permissions.")
public class Role extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du rôle (clé primaire).")
    public Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Nom unique du rôle (ex: ADMIN, MEMBER, LIBRARIAN).")
    public String name;

    @ManyToMany
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Schema(description = "Liste des permissions associées à ce rôle.")
    public List<Permission> permissions;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_active", nullable = false)
    @Schema(description = "Indique si le rôle est actif (true) ou désactivé (false).")
    public Boolean isActive = true;

    @Column(unique = true, nullable = false, length = 20)
    @Schema(description = "Code interne unique du rôle (ex: ROLE-2025-001).")
    public String code;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "Date de création du rôle (timestamp).")
    public Instant createdAt = Instant.now();

    @Column(name = "deleted_at")
    @Schema(description = "Date de suppression logique du rôle, si applicable.")
    public Instant deletedAt;

    @Column(name = "updated_at")
    @Schema(description = "Date de dernière mise à jour du rôle.")
    public Instant updatedAt;

    @Column(name = "deleted", nullable = false)
    @Schema(description = "Indique si le rôle est supprimé logiquement (soft delete).")
    public Boolean deleted = false;

    // Génération automatique du code si non fourni
    @PrePersist
    public void generateCode() {
        if (this.code == null) {
            String prefix = "ROLE";
            String timestamp = String.valueOf(System.currentTimeMillis()).substring(7); // suffixe court
            this.code = prefix + "-" + timestamp;
        }
    }
}
