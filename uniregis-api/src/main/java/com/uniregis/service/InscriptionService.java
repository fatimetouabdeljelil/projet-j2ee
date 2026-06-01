package com.uniregis.service;

import com.uniregis.dto.InscriptionDTO;
import com.uniregis.entity.Inscription;
import com.uniregis.entity.Etudiant; // Ne pas oublier d'importer l'entité Etudiant
import com.uniregis.event.InscriptionEvent;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class InscriptionService {

    @PersistenceContext(unitName = "UniRegisPU")
    private EntityManager em;

    @Inject 
    private Event<InscriptionEvent> evt;

    @Transactional
    public Inscription creer(InscriptionDTO dto) {

        Inscription ins = new Inscription();

        // CORRECTION : On cherche l'étudiant dans la base de données grâce à son matricule/id
        // Si votre base utilise le Matricule comme Clé Primaire de l'Etudiant :
       Etudiant etudiant = em.find(Etudiant.class, dto.id());
        
        // On associe l'objet Etudiant trouvé à l'inscription (au lieu de setMatricule)
        ins.setEtudiant(etudiant);
        ins.setStatut(dto.statut());

        // Enregistrement de l'inscription
        em.persist(ins);

        // Déclenchement de l'événement CDI
        if (evt != null) {
            evt.fire(new InscriptionEvent(dto.matricule()));
        }

        return ins;
    }
}