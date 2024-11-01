package com.example

import com.example.model.PostgresTaskRepository
import com.example.plugins.*
import com.example.repository.PostgresUserRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val taskRepo = PostgresTaskRepository()
    val userRepo = PostgresUserRepository()
    configureSockets()
    configureFrameworks()
    configureSerialization(taskRepo, userRepo)
    configureDatabases()
    configureSecurity()
    configureRouting()
}
