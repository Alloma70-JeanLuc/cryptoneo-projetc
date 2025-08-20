package org.crypto.helpers;


import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class Utilities {

    /**
     * Génère une chaîne de caractères aléatoire unique.
     * @return Une référence unique.
     */
    public String generateUniqueReference() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Valide si une chaîne est non nulle et non vide.
     * @param str La chaîne à valider.
     * @return true si la chaîne est valide, sinon false.
     */
    public boolean isStringValid(String str) {
        return str != null && !str.trim().isEmpty();
    }
}