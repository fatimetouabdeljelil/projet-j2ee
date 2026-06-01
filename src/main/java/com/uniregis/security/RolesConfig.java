package com.uniregis.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.security.DeclareRoles;

@DeclareRoles({"ADMIN", "PROFESSOR"})
@ApplicationScoped
public class RolesConfig {
}