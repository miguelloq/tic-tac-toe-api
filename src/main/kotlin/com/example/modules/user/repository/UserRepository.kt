package com.example.modules.user.repository

import com.example.modules.user.domain.model.User
import kotlinx.serialization.Serializable

interface UserRepository {
    suspend fun create(user: User)
    suspend fun all(): List<Pair<Long,User>>
}