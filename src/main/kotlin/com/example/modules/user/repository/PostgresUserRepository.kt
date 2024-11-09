package com.example.modules.user.repository

import com.example.core.repository.suspendTransaction
import com.example.modules.user.domain.model.User

class PostgresUserRepository(): UserRepository {
    override suspend fun create(user: User): Unit = suspendTransaction{
        UserEntity.new {
            name = user.name.value
            email = user.email.value
            password = user.password.value
        }
    }

    override suspend fun all(): List<User> = suspendTransaction {
        UserEntity
            .all()
            .map{ it.toModel() }
    }

}