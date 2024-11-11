package com.example.modules.user.route

import com.example.core.plugins.getTokenConfig
import com.example.core.plugins.getUserId
import com.example.modules.user.domain.model.User
import com.example.modules.user.domain.usecase.GetAllUserUsecase
import com.example.modules.user.domain.usecase.LoginRequestDto
import com.example.modules.user.domain.usecase.LoginUserUsecase
import com.example.modules.user.domain.usecase.RegisterUserDto
import com.example.modules.user.domain.usecase.RegisterUserUsecase
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Route.usersRoute(
    getAllUserUsecase: GetAllUserUsecase = application.inject<GetAllUserUsecase>().value,
    registerUserUsecase: RegisterUserUsecase = application.inject<RegisterUserUsecase>().value,
    loginUserUsecase: LoginUserUsecase  = application.inject<LoginUserUsecase>().value
) = route("/users"){

    authenticate("core-auth"){
        get(){
            val users = getAllUserUsecase()
            val response = users.map{ it.toGetAllUser() }
            call.respond(response)
        }
    }

    get("login"){
        try {
            val tokenConfig = application.getTokenConfig()
            val dto = call.receive<LoginRequestDto>()
            val token = loginUserUsecase(tokenConfig,dto)
            call.respond(hashMapOf("token" to token))
        }catch (_: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    post{
        try {
            val dto = call.receive<RegisterUserDto>()
            registerUserUsecase(dto)
            call.respond(HttpStatusCode.NoContent)
        }catch(_: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    authenticate("core-auth"){
        get("id"){
            val id = getUserId() ?: call.respond(HttpStatusCode.Unauthorized)
            call.respond(id)
        }
    }
}

@Serializable data class GetUserResponseDto(val id:Long, val name: String)
fun Pair<Long, User>.toGetAllUser() = GetUserResponseDto(first, second.name.value)