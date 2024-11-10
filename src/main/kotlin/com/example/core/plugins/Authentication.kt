package com.example.core.plugins

import com.example.core.service.AuthenticationService
import io.ktor.server.application.Application
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.jwt
import org.koin.ktor.ext.inject

fun Application.configureAuthentication(){
    val authenticationService by inject<AuthenticationService>()

    authentication {
        jwt("core-auth"){
            realm = authenticationService.realm
            verifier(authenticationService.jwtVerifier)

            validate { credential ->
                authenticationService.validateToken(credential)
            }
        }
    }
}