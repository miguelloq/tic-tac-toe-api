package com.example.core.plugins

import com.example.modules.task.route.taskRoute
import com.example.modules.user.route.usersRoute
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing

fun Application.configureRouting(){
    install(ContentNegotiation) { json() }
    routing{
        taskRoute()
        usersRoute()
    }
}