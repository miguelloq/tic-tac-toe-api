package com.example.core.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.JWTCredential
import io.ktor.server.auth.jwt.JWTPrincipal
import java.util.Date

class AuthenticationService() {
    // ENV
    private val secret = "abcde"

    //Config
    private val issuer = "http://localhost"
    private val audience = "my-audience"
    val realm = "my realm"

    val jwtVerifier = JWT
        .require(Algorithm.HMAC256(secret))
        .withAudience(audience)
        .withIssuer(issuer)
        .build()

    fun createToken(id: Long): String = JWT
        .create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withClaim("id",id)
        .withExpiresAt(Date(System.currentTimeMillis() + 3_600_000))
        .sign(Algorithm.HMAC256(secret))

    fun validateToken(credential: JWTCredential): JWTPrincipal?{
        val userId = credential.payload.getClaim("id").asString()
        //TODO VERIFY IF USERID EXISTS IN THE DATABASE
        val audienceMatches = credential.payload.audience.contains(audience)
        return if(audienceMatches) JWTPrincipal(credential.payload) else null
    }
}