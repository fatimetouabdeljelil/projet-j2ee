package com.uniregis.resource;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.Operation;

import com.uniregis.dto.InscriptionDTO;
import com.uniregis.entity.Inscription;
import com.uniregis.service.InscriptionService;
import java.util.List;

@Path("/inscriptions")
@RequestScoped
@OpenAPIDefinition(
    info = @Info(
        title = "UniRegis",
        version = "1.0"
    )
)
public class InscriptionResource {

    @PersistenceContext(unitName = "UniRegisPU")
    private EntityManager em;

    @Inject
    InscriptionService service;

    // MODIFICATION : Cette méthode retourne maintenant la liste réelle des inscriptions au format JSON
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Afficher toutes les inscriptions")
    public List<Inscription> getAll() {
        // Récupère toutes les inscriptions et les étudiants associés depuis PostgreSQL
        return em.createQuery("SELECT i FROM Inscription i", Inscription.class).getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Créer une inscription")
    public Response inscrire(@Valid InscriptionDTO dto) {

        Inscription created = service.creer(dto);

        return Response
                .status(Response.Status.CREATED)
                .entity(created)
                .build();
    }
}