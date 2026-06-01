package com.uniregis.dto;

import com.uniregis.entity.Statut;

public record InscriptionDTO (Long id, String matricule, Statut statut) {}