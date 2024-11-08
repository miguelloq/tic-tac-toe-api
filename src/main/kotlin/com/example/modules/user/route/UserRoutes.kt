package com.example.modules.user.route

import com.example.modules.user.repository.UserRepository
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Routing.usersRoute() = route("/users"){
    val userRepo by application.inject<UserRepository>()

    get{ call.respond(userRepo.all()) }

    get("/tasks"){ call.respond(userRepo.allWithTasks()) }

}

