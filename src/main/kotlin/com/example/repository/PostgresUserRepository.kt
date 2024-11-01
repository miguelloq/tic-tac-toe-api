package com.example.repository

import com.example.db.TaskTable
import com.example.db.UserEntity
import com.example.db.UserTable
import com.example.db.suspendTransaction
import com.example.db.toModel
import com.example.model.User

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