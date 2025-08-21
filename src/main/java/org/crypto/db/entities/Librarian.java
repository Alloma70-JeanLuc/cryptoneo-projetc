package org.crypto.db.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("LIBRARIAN")
public class Librarian extends Person{


}
