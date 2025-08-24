package org.crypto.resource.roles;


import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.db.entities.Role;
import org.crypto.db.repositories.RoleRepository;
import org.crypto.resource.roles.dto.RoleDTO;


@Path("/roles")
public class RolesManager extends CrudEndPointImpl<Role> {

    @Inject
    RoleRepository repo;

    @Inject
    public void initRepo(){
        setT(repo);
    }

    @POST
    @Transactional
    public Response createRole(RoleDTO roleDTO) {

        // 1. Vérifier si le role existe déjà
        if (repo.findByName(roleDTO.name) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Role already exists")
                    .build();
        }

        // 2. Créer et persister le Role
        Role role = new Role();
        role.name = roleDTO.name;
        role.code = roleDTO.code;
        role.isActive = roleDTO.isActive;

        repo.persist(role);

        // 3. Retourner un DTO minimal
        RoleDTO response = new RoleDTO();
        response.name = role.name;
        response.code = role.code;
        response.isActive = role.isActive;

        return Response.ok(response).build();
    }



}
