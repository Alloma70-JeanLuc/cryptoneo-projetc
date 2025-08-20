package org.crypto.core.features;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Définit des ensembles de réponses API standards et réutilisables pour la documentation OpenAPI.
 */
public interface DefaultApiResponses {

    @APIResponse(responseCode = "404", description = "La ressource demandée n'a pas été trouvée.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @interface DefaultGETResponses {}

    @APIResponse(responseCode = "400", description = "La requête est invalide (données manquantes ou incorrectes).",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @interface DefaultPOSTResponses {}

    @APIResponse(responseCode = "404", description = "La ressource à supprimer n'a pas été trouvée.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @interface DefaultDELETEResponses {}
}