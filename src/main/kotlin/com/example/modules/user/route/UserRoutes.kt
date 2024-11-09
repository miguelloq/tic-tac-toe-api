package com.example.modules.user.route

import com.example.modules.user.domain.usecase.RegisterUserDto
import com.example.modules.user.domain.usecase.RegisterUserUsecase
import com.example.modules.user.repository.UserRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Routing.usersRoute() = route("/users"){
    val userRepo by application.inject<UserRepository>()
    val registerUserUsecase by application.inject<RegisterUserUsecase>()

    get{ call.respond(userRepo.all()) }

    post{
        try {
            val dto = call.receive<RegisterUserDto>()
            registerUserUsecase(dto)
            call.respond(HttpStatusCode.NoContent)
        }catch(e: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}



