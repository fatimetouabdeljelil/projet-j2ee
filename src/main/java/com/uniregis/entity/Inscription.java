package com.uniregis.entity;

import jakarta.persistence.*;

@Entity
public class Inscription {
@Id @GeneratedValue private Long id;
@ManyToOne (fetch
= FetchType. LAZY) private Etudiant etudiant;
@Enumerated private Statut statut;
@Version private Long version; // Optimistic Lock

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }
    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}