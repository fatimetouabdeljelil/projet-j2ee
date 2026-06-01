package com.uniregis.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@DataSourceDefinition(
    name = "java:app/jdbc/UniRegisDS",
    className = "org.postgresql.ds.PGSimpleDataSource",
    url = "jdbc:postgresql://uniregis-db:5432/uniregis_db",
    user = "uniregis_user",
    password = "uniregis_password"
)
@ApplicationPath("/api/v1")
public class ApplicationConfig extends Application {
}