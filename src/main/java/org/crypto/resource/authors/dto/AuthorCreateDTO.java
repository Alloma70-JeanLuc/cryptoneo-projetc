package org.crypto.resource.authors.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Un "record" Java est parfait pour un DTO immuable
public record AuthorCreateDTO(
        @NotBlank(message = "Le prénom ne peut pas être vide")
        String firstName,

        @NotBlank(message = "Le nom de famille ne peut pas être vide")
        String lastName,

        @NotBlank(message = "L'email ne peut pas être vide")
        @Email(message = "Le format de l'email est invalide")
        String email,

        String biography
) {}