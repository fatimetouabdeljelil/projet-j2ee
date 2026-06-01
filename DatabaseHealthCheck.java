package com.uniregis.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class DatabaseHealthCheck implements HealthCheck {

    // REMPLACEZ @Inject PAR @PersistenceContext ICI AUSSI
    @PersistenceContext(unitName = "UniRegisPU")
    private EntityManager em;

    @Override
    public HealthCheckResponse call() {
        try {
            // Exécute une requête simple pour vérifier si PostgreSQL répond
            em.createNativeQuery("SELECT 1").getSingleResult();
            return HealthCheckResponse.up("Database connection is healthy");
        } catch (Exception e) {
            return HealthCheckResponse.down("Database connection failed: " + e.getMessage());
        }
    }
}