package com.example.modules.user.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.core.model.TokenConfig
import java.util.Date

class CreateTokenServiceImpl: CreateTokenService {
    override fun getToken(userId: Long,tokenConfig: TokenConfig): String = JWT
        .create()
        .withAudience(tokenConfig.audience)
        .withIssuer(tokenConfig.issuer)
        .withClaim("id",userId)
        .withExpiresAt(Date(System.currentTimeMillis() + 3_600_000))
        .sign(Algorithm.HMAC256(tokenConfig.secret))
}