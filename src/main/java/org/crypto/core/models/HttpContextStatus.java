package org.crypto.core.models;

/**
 * Énumération pour centraliser les codes de statut et les messages personnalisés de l'application.
 * Cela permet d'avoir une référence unique pour toutes les réponses de l'API.
 */
public enum HttpContextStatus {
    // Succès
    SUCCESS(7000, "Success"),
    CREATED(7001, "Resource created successfully"),

    // Erreurs client (8xxx)
    BAD_REQUEST(8000, "Bad Request"),
    UNAUTHORIZED(8001, "Unauthorized"),
    FORBIDDEN(8002, "Forbidden"),
    MISSING_PARAMETERS(8003, "Required parameters are missing"),
    NOT_FOUND(8004, "Resource not found"),
    VALIDATION_ERROR(8005, "A validation error occurred"),

    // Erreurs serveur (9xxx)
    INTERNAL_SERVER_ERROR(9000, "An internal server error occurred"),
    DATABASE_ERROR(9001, "A database error occurred"),
    PAYMENT_GATEWAY_ERROR(9002, "Payment gateway error");


    private final int code;
    private final String message;

    HttpContextStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}