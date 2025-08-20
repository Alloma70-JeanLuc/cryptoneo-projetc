package org.crypto.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "book_loans")
public class BookLoan extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(optional = false)
    public Book book;

    @ManyToOne(optional = false)
    public Member member;

    public Instant loanDate = Instant.now();
    public Instant dueDate;
    public Instant returnDate;

    public boolean returned = false;
}
