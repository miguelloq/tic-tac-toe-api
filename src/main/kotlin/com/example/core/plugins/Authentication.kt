package com.example.core.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.core.model.TokenConfig
import com.example.core.repository.CoreUserEntity
import com.example.core.repository.suspendTransaction
import io.ktor.server.application.Application
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.auth.principal
import io.ktor.server.routing.RoutingContext
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

fun Application.configureAuthentication(){
    val tokenConfig = getTokenConfig()

    authentication {
        jwt("core-auth"){
            realm = tokenConfig.realm
            verifier(JWT
                .require(Algorithm.HMAC256(tokenConfig.secret))
                .withAudience(tokenConfig.audience)
                .withIssuer(tokenConfig.issuer)
                .build())

            validate { credential ->
                val audienceMatches = credential.payload.audience.contains(tokenConfig.audience)
                return@validate if(audienceMatches) JWTPrincipal(credential.payload) else null
            }
        }
    }
}

fun Application.getTokenConfig() = TokenConfig(
    secret = environment.config.property("jwt.secret").getString(),
    issuer = environment.config.property("jwt.issuer").getString(),
    audience = environment.config.property("jwt.audience").getString(),
    realm = environment.config.property("jwt.realm").getString()
)

suspend fun RoutingContext.getUserId(): Long?{
    try{
        val userId = call.principal<JWTPrincipal>()
            ?.getClaim("id",Long::class)
            ?.toLong()
            ?: return null
        return suspendTransaction {
            return@suspendTransaction if(userExistsInDatabase(userId)) userId else null
        }
    }catch(_: Exception){
        return null
    }
}

private fun userExistsInDatabase(id: Long): Boolean =
    CoreUserEntity.findById(id.toInt()) != null

private object CoreUserTable: IntIdTable("users")

private class CoreUserEntity(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<CoreUserEntity>(CoreUserTable)
}




