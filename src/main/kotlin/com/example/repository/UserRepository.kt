package com.example.repository

import com.example.model.Task
import com.example.model.User

interface UserRepository {
    suspend fun create(user: User)
    suspend fun all(): List<User>
    suspend fun allWithTasks(): List<User>
}