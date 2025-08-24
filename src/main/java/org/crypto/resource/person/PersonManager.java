package org.crypto.resource.person;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.db.entities.Person;
import org.crypto.db.repositories.PersonRepository;
import org.crypto.resource.person.dto.PersonDTO;

import java.util.List;
import java.util.stream.Collectors;

@Path("/persons")
@ApplicationScoped // Il est préférable d'utiliser @ApplicationScoped pour les ressources
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonManager extends CrudEndPointImpl<Person> {

    @Inject
    PersonRepository repo;

    @Inject
    public void initRepo(){ // Le nom de la méthode doit commencer par une minuscule
        setT(repo);
    }

    // --- CORRECTION : Méthode pour lister toutes les personnes ---
    @GET
    public Response getAllPersons() {
        // 1. Récupérer la liste des entités Person depuis la base de données
        List<Person> personList = repo.listAll();

        // 2. Convertir (mapper) la liste d'entités en une liste de DTOs
        List<PersonDTO> dtoList = personList.stream()
                .map(this::mapEntityToDto) // Utilise une méthode de mapping
                .collect(Collectors.toList());

        // 3. Retourner la liste de DTOs avec un statut OK (200)
        return Response.ok(dtoList).build();
    }

    // --- AJOUT : Exemple de méthode pour créer une personne (bonne pratique) ---
    @POST
    @Transactional
    public Response createPerson(PersonDTO dto) { // On reçoit un DTO, pas une entité
        // 1. Valider le DTO (peut être fait avec Hibernate Validator sur le DTO)

        // 2. Convertir (mapper) le DTO en une nouvelle entité Person
        Person newPerson = mapDtoToEntity(dto);

        // 3. Persister la nouvelle entité
        repo.persist(newPerson);

        // 4. (Optionnel mais recommandé) Renvoyer le DTO de la ressource créée,
        //    qui inclura les champs générés par la base de données (id, code, createdAt)
        PersonDTO resultDto = mapEntityToDto(newPerson);

        // 5. Retourner une réponse avec le statut CREATED (201) et la nouvelle ressource
        return Response.status(Response.Status.CREATED).entity(resultDto).build();
    }

    // --- Méthodes de mapping (helpers) ---

    /**
     * Convertit une entité Person en PersonDTO.
     */
    private PersonDTO mapEntityToDto(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.type = person.type;
        dto.code = person.code;
        dto.firstName = person.firstName;
        dto.lastName = person.lastName;
        dto.email = person.email;
        dto.isActive = person.isActive;
        dto.createdAt = person.createdAt;
        dto.updatedAt = person.updatedAt;
        dto.deletedAt = person.deletedAt;
        dto.deleted = person.deleted;
        return dto;
    }

    /**
     * Convertit un PersonDTO en une nouvelle entité Person.
     */
    private Person mapDtoToEntity(PersonDTO dto) {
        Person person = new Person();
        person.type = dto.type;
        person.firstName = dto.firstName;
        person.lastName = dto.lastName;
        person.email = dto.email;
        // Les autres champs (code, createdAt, isActive, deleted) sont gérés
        // par la logique @PrePersist de l'entité, c'est donc plus sûr de ne pas les setter ici.
        return person;
    }
}