package com.example.modules.user.repository

import com.example.modules.user.model.User

interface UserRepository {
    suspend fun create(user: User)
    suspend fun all(): List<User>
}