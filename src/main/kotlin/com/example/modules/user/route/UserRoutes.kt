package com.example.modules.user.route

import com.example.modules.user.domain.usecase.GetAllUserUsecase
import com.example.modules.user.domain.usecase.RegisterUserDto
import com.example.modules.user.domain.usecase.RegisterUserUsecase
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.usersRoute() = route("/users"){
    val getAllUserUsecase by application.inject<GetAllUserUsecase>()
    val registerUserUsecase by application.inject<RegisterUserUsecase>()

    get{
        try {
            val users = getAllUserUsecase()
            call.respond(users)
        }catch(e: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }
    }

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



