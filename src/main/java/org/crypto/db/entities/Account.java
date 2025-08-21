package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "accounts")
public class Account extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true, nullable = false)
    public String email;

    @Column(nullable = false)
    public String password; // hashé (BCrypt recommandé)

    @ManyToOne
    public Role role;

    @OneToOne // Un compte est lié à une seule personne
    @JoinColumn(name = "person_id") // Clé étrangère dans la table 'accounts'
    public Person person;

    public Instant createdAt = Instant.now();
    public boolean active = true;
}

