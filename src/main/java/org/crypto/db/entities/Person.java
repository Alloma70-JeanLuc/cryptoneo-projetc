package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Entity
@Table(name = "persons")
@Schema(description = "Représente une personne dans le système")
public class Person extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "type", nullable = false)
    public String type;

    // ----- CORRECTION : Spécifier explicitement les noms des colonnes -----
    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(unique = true, nullable = false)
    public String email;

    @NotNull
    @Column(name = "active")
    @ColumnDefault("true")
    public Boolean isActive = true;

    @Column(unique = true, length = 20)
    public String code;

    @NotNull
    @Column(name = "created_at", updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    public Instant createdAt;

    @Column(name = "updated_at")
    public Instant updatedAt;

    @Column(name = "deleted_at")
    public Instant deletedAt;

    @Column(name = "deleted")
    @ColumnDefault("false")
    public Boolean deleted = false;

    @PrePersist
    public void prePersist() {
        if (this.code == null) {
            this.code = "PERS-" + String.valueOf(System.currentTimeMillis()).substring(7);
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