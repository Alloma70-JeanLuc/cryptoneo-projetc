package org.crypto.core.features;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.ws.rs.core.Response;
import org.crypto.core.models.HttpContextStatus;

import java.io.Serializable;

/**
 * Classe générique pour standardiser les réponses de l'API.
 * @param <T> Le type de données à inclure dans la réponse.
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // N'inclut pas les champs nuls dans la réponse JSON
public class ApiResponse<T> implements Serializable {

    private int status_code;
    private String status_message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int statusCode, String statusMessage, T data) {
        this.status_code = statusCode;
        this.status_message = statusMessage;
        this.data = data;
    }

    public ApiResponse(HttpContextStatus status, T data) {
        this.status_code = status.getCode();
        this.status_message = status.getMessage();
        this.data = data;
    }

    // Getters et Setters
    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * Méthode utilitaire pour construire une réponse JAX-RS standard.
     * @param status Le statut HTTP standard (ex: 200, 404).
     * @param apiResponse L'objet ApiResponse à envoyer.
     * @return Une réponse JAX-RS complète.
     */
    public static <T> Response build(Response.Status status, ApiResponse<T> apiResponse) {
        return Response.status(status).entity(apiResponse).build();
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(Response.Status.OK.getStatusCode(), message, data);
    }

    /**
     * Crée une réponse pour une ressource non trouvée (404 Not Found).
     */
    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(Response.Status.NOT_FOUND.getStatusCode(), message, null);
    }

    /**
     * Crée une réponse pour une erreur interne du serveur (500 Internal Server Error).
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), message, null);
    }
}
