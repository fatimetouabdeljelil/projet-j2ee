package com.uniregis.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class DatabaseProducer {

    // Injecte l'unité de persistance gérée par le serveur d'application Payara
    @PersistenceContext(unitName = "UniRegisPU")
    private EntityManager em;

    /**
     * Produit un EntityManager disponible pour l'injection CDI via @Inject.
     * Le scope @RequestScoped est recommandé pour que chaque requête HTTP ait son propre cycle de vie.
     */
    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return this.em;
    }

    /**
     * Libère proprement les ressources de l'EntityManager à la fin de la requête.
     */
    public void closeEntityManager(@Disposes EntityManager em) {
        // Le conteneur gère la fermeture puisque c'est un @PersistenceContext,
        // mais cette méthode d'élimination (Disposes) sécurise le cycle de vie CDI.
    }
}