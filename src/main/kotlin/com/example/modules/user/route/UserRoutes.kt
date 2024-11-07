package com.example.modules.user.route

import com.example.modules.user.repository.PostgresUserRepository
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Routing.userRoute() = route("/users"){
    val userRepo = PostgresUserRepository()
    //by inject<UserRepository>()

    get{ call.respond(userRepo.all()) }

    get("/tasks"){ call.respond(userRepo.allWithTasks()) }

}

