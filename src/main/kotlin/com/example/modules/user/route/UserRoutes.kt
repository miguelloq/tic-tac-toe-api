package com.example.modules.user.route

import com.example.modules.task.repository.TaskRepository
import com.example.modules.user.repository.UserRepository
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Routing.usersRoute(userRepo: UserRepository) = route("/users"){


    get{ call.respond(userRepo.all()) }

    get("/tasks"){ call.respond(userRepo.allWithTasks()) }

}

