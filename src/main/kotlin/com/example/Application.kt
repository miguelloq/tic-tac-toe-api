package com.example

import com.example.modules.task.route.taskRoute
import com.example.modules.task.taskKoinModule
import com.example.modules.user.route.userRoute
import com.example.modules.user.userKoinModule
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/ktor_tutorial_db",
        user = "postgres",
        password = "password"
    )

    install(ContentNegotiation) { json() }
    install(Koin) {
        slf4jLogger()
        modules(taskKoinModule, userKoinModule)
    }

    routing{
        userRoute()
        taskRoute()
    }
}


