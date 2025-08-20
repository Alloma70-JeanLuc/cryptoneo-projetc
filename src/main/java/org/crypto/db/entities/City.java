package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class City extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String name;

    // Optionnel : relation inverse si tu veux lister tous les auteurs d'une ville
    // @OneToMany(mappedBy = "city")
    // public List<Author> authors;
}
