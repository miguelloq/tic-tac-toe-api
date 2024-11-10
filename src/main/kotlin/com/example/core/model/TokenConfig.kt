package com.example.core.model

import io.ktor.server.application.Application

data class TokenConfig(
    val secret: String,
    val issuer: String,
    val audience: String,
    val realm:String
)




