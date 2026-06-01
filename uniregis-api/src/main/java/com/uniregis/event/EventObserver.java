package com.uniregis.event;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import java.util.logging.Logger;

@ApplicationScoped
public class EventObserver {
    private static final Logger LOGGER = Logger.getLogger(EventObserver.class.getName());

    public void onInscriptionCreated(@Observes InscriptionEvent event) {
        LOGGER.info("Événement reçu : Traitement de l'inscription pour " + event.matricule());
    }
}