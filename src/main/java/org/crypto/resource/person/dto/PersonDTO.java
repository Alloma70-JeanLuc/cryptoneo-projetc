package org.crypto.resource.person.dto;

import org.crypto.core.crud.CrudEndPointImpl;

import java.time.Instant;

public class PersonDTO{
    public String type;
    public String code;
    public String firstName;
    public String lastName;
    public String email;
    public Boolean isActive = true;
    public Instant createdAt;
    public Instant updatedAt;
    public Instant deletedAt;
    public Boolean deleted = false;
}