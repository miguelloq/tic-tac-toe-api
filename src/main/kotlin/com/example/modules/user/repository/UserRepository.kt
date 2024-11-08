package com.example.modules.user.repository

import com.example.modules.user.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserDto(val name: String, val email: String, val password: String)

interface UserRepository {
    suspend fun create(user: User)
    suspend fun all(): List<User>
}