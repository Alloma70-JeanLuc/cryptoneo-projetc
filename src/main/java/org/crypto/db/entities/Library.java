package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Entity
@Table(name = "libraries")
@Schema(description = "Représente une bibliothèque, avec son adresse et sa ville.")
public class Library extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de la bibliothèque (clé primaire).")
    public Long id;

    @Schema(description = "Adresse physique de la bibliothèque.")
    @Column(length = 255)
    public String address;

    @ManyToOne
    @Schema(description = "Ville où se situe la bibliothèque (relation ManyToOne).")
    public City city;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_active", nullable = false)
    @Schema(description = "Indique si la bibliothèque est active (true) ou désactivée (false).")
    public Boolean isActive = true;

    @Column(unique = true, nullable = false, length = 20)
    @Schema(description = "Code interne unique de la bibliothèque (ex: LIBR-2025-001).")
    public String code;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "Date de création de la bibliothèque (timestamp).")
    public Instant createdAt = Instant.now();

    @Column(name = "deleted_at")
    @Schema(description = "Date de suppression logique de la bibliothèque, si applicable.")
    public Instant deletedAt;

    @Column(name = "updated_at")
    @Schema(description = "Date de dernière mise à jour des informations de la bibliothèque.")
    public Instant updatedAt;

    @Column(name = "deleted", nullable = false)
    @Schema(description = "Indique si la bibliothèque est supprimée logiquement (soft delete).")
    public Boolean deleted = false;

    public Library(){}

    public Library(String address, City city){
        this.address = address;
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(City city) {
        this.city = city;
    }

    // Génération automatique du code si non fourni
    @PrePersist
    public void generateCode() {
        if (this.code == null) {
            String prefix = "LIBR";
            String timestamp = String.valueOf(System.currentTimeMillis()).substring(7); // suffixe court
            this.code = prefix + "-" + timestamp;
        }
    }
}
