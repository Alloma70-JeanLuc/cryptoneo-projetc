package org.crypto.resource.auth;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// Importer toutes les entités et repositories nécessaires de manière explicite
import org.crypto.core.features.ApiResponse;
import org.crypto.db.entities.*;
import org.crypto.db.repositories.*;
import org.mindrot.jbcrypt.BCrypt;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthManager {

    @Inject AccountRepository accountRepo;
    @Inject RoleRepository roleRepo;


    public static class LoginRequest {
        public String email;
        public String password;
    }

    public static class SignUpRequest {
        public String firstName;
        public String lastName;
        public String email;
        public String password;
    }

    @POST
    @Path("/signup")
    @Transactional
    public Response signup(SignUpRequest request) {

        // 1. Vérifier si l'email est déjà utilisé
        if (accountRepo.findByEmail(request.email) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(ApiResponse.error("Email already in use"))
                    .build();
        }

        // 2. Rôle par défaut : USER
        Role role = roleRepo.findByName("USER");
        if (role == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ApiResponse.error("Default role USER not found."))
                    .build();
        }

        // 3. Créer la Person
        Person newPerson = new Person();
        newPerson.firstName = request.firstName;
        newPerson.lastName = request.lastName;
        newPerson.email = request.email;
        newPerson.isActive = true;
        newPerson.createdAt = Instant.now();
        newPerson.type = "Person";
        newPerson.persist();

        // 4. Hasher le mot de passe
        String hashedPassword = BCrypt.hashpw(request.password, BCrypt.gensalt());

        // 5. Créer le compte
        Account account = new Account();
        account.email = request.email;
        account.password = hashedPassword;
        account.role = role;
        account.person = newPerson;
        account.isActive = true;
        account.createdAt = Instant.now();
        accountRepo.persist(account);

        return Response.status(Response.Status.CREATED)
                .entity(ApiResponse.success(null, "Account created successfully as USER"))
                .build();
    }


    @POST
    @Path("/login")
    @Transactional
    public Response login(LoginRequest request) {

        // 1. Vérifier si l'email existe
        Account account = accountRepo.findByEmail(request.email);
        if (account == null || !account.isActive) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(ApiResponse.error("Invalid credentials"))
                    .build();
        }

        // 2. Vérifier le mot de passe
        if (!BCrypt.checkpw(request.password, account.password)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(ApiResponse.error("Invalid credentials"))
                    .build();
        }

        String token = Jwt.issuer("crypto-api")
                .upn(account.email)
                .groups(Set.of(account.role.name)) // Utilisez .groups() qui attend un Set de chaînes
                .expiresIn(3600)
                .sign();

        // 4. Retourner le token et quelques infos utiles
        Map<String, Object> response = Map.of(
                "token", token,
                "email", account.email,
                "role", account.role.name,
                "personId", account.person.id
        );

        return Response.ok(ApiResponse.success(response, "Login successful")).build();
    }
}