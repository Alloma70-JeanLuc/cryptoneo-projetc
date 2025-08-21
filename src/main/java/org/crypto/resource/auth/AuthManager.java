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
import org.crypto.db.entities.Library;
import org.crypto.db.repositories.*;
import org.crypto.db.repositories.LibraryRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashSet;
import java.util.Map;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthManager {

    @Inject AccountRepository accountRepo;
    @Inject RoleRepository roleRepo;
    @Inject MemberRepository memberRepo;
    @Inject AuthorRepository authorRepo;
    @Inject
    LibrarianRepository librarianRepo; // <-- CORRECTION: Injection du bon repository

    public static class LoginRequest {
        public String email;
        public String password;
    }

    public static class SignUpRequest {
        public String firstName;
        public String lastName;
        public String email;
        public String password;
        public String roleName; // Obligatoire pour savoir quel type de personne créer
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

        // 2. Déterminer le rôle
        String roleName = (request.roleName != null && !request.roleName.isEmpty()) ? request.roleName.toUpperCase() : "MEMBER";
        Role role = roleRepo.findByName(roleName);
        if (role == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ApiResponse.error("Role '" + roleName + "' not found."))
                    .build();
        }

        // 3. Créer la Personne en fonction du rôle (LOGIQUE CORRIGÉE)
        Person newPerson;
        try {
            switch (roleName) {
                case "AUTHOR":
                    Author author = new Author();
                    author.firstName = request.firstName;
                    author.lastName = request.lastName;
                    author.email = request.email;
                    authorRepo.persist(author);
                    newPerson = author;
                    break;

                case "LIBRARIAN":
                    // <-- CORRECTION MAJEURE: On crée un Librarian (personne) et non une Library (lieu)
                    Librarian librarian = new Librarian();
                    librarian.firstName = request.firstName;
                    librarian.lastName = request.lastName;
                    librarian.email = request.email;
                    librarianRepo.persist(librarian); // On utilise le bon repository
                    newPerson = librarian;
                    break;

                case "MEMBER":
                default:
                    Member member = new Member();
                    member.firstName = request.firstName;
                    member.lastName = request.lastName;
                    member.email = request.email;
                    memberRepo.persist(member);
                    newPerson = member;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ApiResponse.error("An error occurred while creating the user profile."))
                    .build();
        }


        // 4. Hasher le mot de passe
        String hashedPassword = BCrypt.hashpw(request.password, BCrypt.gensalt());

        // 5. Créer le Compte et l'associer à la Personne et au Rôle
        Account account = new Account();
        account.email = request.email;
        account.password = hashedPassword;
        account.role = role;
        account.person = newPerson; // Liaison générique à la personne créée

        accountRepo.persist(account);

        return Response.status(Response.Status.CREATED)
                .entity(ApiResponse.success(null, "Account created successfully as " + roleName))
                .build();
    }

    @POST
    @Path("/login")
    @Transactional
    public Response login(LoginRequest request) {
        ApiResponse<Object> response = new ApiResponse<>();

        try {
            // 1. Vérifier si l'account existe
            Account account = accountRepo.findByEmail(request.email);
            if (account == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(ApiResponse.error("Invalid email or password"))
                        .build();
            }

            // 2. Vérifier le mot de passe
            if (!BCrypt.checkpw(request.password, account.password)) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(ApiResponse.error("Invalid email or password"))
                        .build();
            }

            // 3. Générer le token JWT
            String token = Jwt.issuer("crypto-app") // ⚡ tu peux configurer ça dans application.properties
                    .upn(account.email) // "principal name"
                    .groups(account.role.name) // rôle(s)
                    .claim("id", account.id) // id du compte
                    .claim("personId", account.person.id) // id de la personne associée
                    .claim("role", account.role.name) // rôle explicite
                    .sign();

            // 4. Réponse avec token + infos
            Map<String, Object> data = Map.of(
                    "token", token,
                    "email", account.email,
                    "role", account.role.name,
                    "person", Map.of(
                            "id", account.person.id,
                            "firstName", account.person.firstName,
                            "lastName", account.person.lastName
                    )
            );

            return Response.ok(ApiResponse.success(data, "Login successful")).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ApiResponse.error("Login failed: " + e.getMessage()))
                    .build();
        }
    }

}