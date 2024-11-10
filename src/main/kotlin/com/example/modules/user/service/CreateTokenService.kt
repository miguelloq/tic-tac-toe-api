package com.example.modules.user.service

import com.example.core.model.TokenConfig

interface CreateTokenService {
    fun getToken(userId: Long, tokenConfig: TokenConfig): String
}