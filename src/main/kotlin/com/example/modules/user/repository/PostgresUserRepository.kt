package com.example.modules.user.repository

import com.example.core.repository.suspendTransaction
import com.example.modules.user.domain.model.User

class PostgresUserRepository(): UserRepository {
    override suspend fun create(user: User): Unit = suspendTransaction{
        UserEntity.new {
            name = user.name.s
            email = user.email.s
            password = user.password.s
        }
    }

    override suspend fun all(): List<User> = suspendTransaction {
        UserEntity
            .all()
            .map{ it.toModel() }
    }

}