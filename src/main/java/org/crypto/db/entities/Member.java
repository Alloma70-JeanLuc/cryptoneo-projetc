package org.crypto.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member extends Person {
    public String membershipNumber;
}
