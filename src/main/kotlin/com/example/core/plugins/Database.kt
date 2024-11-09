package com.example.core.plugins

import io.ktor.server.application.Application
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase(){
    val url = "jdbc:postgresql://localhost:5432/ktor_tutorial_db"
    val user = "postgres"
    val password = "password"

    Database.connect(
        url,
        user = user,
        password = password
    )

    val flyway = Flyway.configure().dataSource(url, user, password).load()
    flyway.migrate()
}