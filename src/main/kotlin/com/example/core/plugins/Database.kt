package com.example.core.plugins

import io.ktor.server.application.Application
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase(){
    val url = environment.config.property("storage.jdbcURL").getString()
    val user = environment.config.property("storage.user").getString()
    val password = environment.config.property("storage.password").getString()
    val driver = environment.config.property("storage.driverClassName").getString()

    Database.connect(
        url,
        user = user,
        password = password,
        driver = driver
    )

    val flyway = Flyway.configure().dataSource(url, user, password).load()
    flyway.migrate()
}