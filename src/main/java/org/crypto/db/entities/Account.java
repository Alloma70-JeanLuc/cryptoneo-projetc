package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "accounts")
public class Account extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du compte (clé primaire).")
    public Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Adresse e-mail unique associée au compte.")
    public String email;

    @Column(nullable = false)
    @Schema(description = "Mot de passe hashé du compte (BCrypt recommandé).")
    public String password;

    @ManyToOne
    @Schema(description = "Rôle associé au compte (ADMIN, ROOT, USER, etc.).")
    public Role role;

    @OneToOne
    @JoinColumn(name = "person_id")
    @Schema(description = "Personne liée à ce compte (relation OneToOne).")
    public Person person;

    @NotNull
    @ColumnDefault("true")
    @Schema(description = "Indique si le compte est actif (true) ou désactivé (false).")
    @Column(name = "active")
    public Boolean isActive;


    @Column(unique = true, nullable = false, length = 20)
    @Schema(description = "Code interne unique d’identification du compte (ex: ACC-123456).")
    public String code;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "Date de création du compte (timestamp).")
    public Instant createdAt;

    @Column(name = "deleted_at")
    @Schema(description = "Date de suppression logique du compte, si applicable.")
    public Instant deletedAt;

    @Column(name = "updated_at")
    @Schema(description = "Date de dernière mise à jour du compte.")
    public Instant updatedAt;

    @Column(name = "deleted")
    @Schema(description = "Indique si le compte est supprimé logiquement (soft delete).")
    public Boolean deleted;

    // ----- CORRECTION : Ajout de la génération automatique des valeurs par défaut -----
    @PrePersist
    public void prePersist() {
        if (this.code == null) {
            // Génère un code unique basé sur le temps, par exemple "ACC-654321"
            this.code = "ACC-" + String.valueOf(System.currentTimeMillis()).substring(7);
        }
        if (this.createdAt == null) {
            this.createdAt = Instant.now();
        }
        if (this.isActive == null) {
            this.isActive = true;
        }
        if (this.deleted == null) {
            this.deleted = false;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}