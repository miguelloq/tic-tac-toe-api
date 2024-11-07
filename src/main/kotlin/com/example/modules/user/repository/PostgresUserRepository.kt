package com.example.modules.user.repository

import com.example.core.repository.suspendTransaction
import com.example.modules.user.model.User

class PostgresUserRepository(): UserRepository {
    override suspend fun create(user: User) {
        UserEntity.new {
            name = user.name
            password = user.password
        }
    }

    override suspend fun all(): List<User> = suspendTransaction {
        UserEntity
            .all()
            .map{ it.toModel(withoutTasks = true) }
    }

    override suspend fun allWithTasks(): List<User> = suspendTransaction {
        UserEntity
            .all()
            .map{ it.toModel() }
    }
}