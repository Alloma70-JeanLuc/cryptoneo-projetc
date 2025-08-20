package org.crypto.resource.auth;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.crypto.db.entities.Account;
import org.crypto.db.repositories.AccountRepository;
import org.eclipse.microprofile.jwt.JsonWebToken;
import io.smallrye.jwt.build.Jwt;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashSet;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AccountRepository accountRepo;

    public static class LoginRequest {
        public String email;
        public String password;
    }

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {

        Account account = accountRepo.findByEmail(request.email);
        if (account == null || !BCrypt.checkpw(request.password, account.password)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\": \"Invalid credentials\"}").build();
        }

        String token = Jwt.issuer("library-api")
                .upn(account.email)
                .groups(new HashSet<>() {{ add(account.role.name); }})
                .expiresIn(3600)
                .sign();

        return Response.ok("{\"token\": \"" + token + "\"}").build();
    }
}
