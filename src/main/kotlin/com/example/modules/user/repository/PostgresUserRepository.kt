package com.example.modules.user.repository

import com.example.core.repository.suspendTransaction
import com.example.modules.user.domain.error.UserError
import com.example.modules.user.domain.model.User
import com.example.modules.user.domain.model.User.Email
import com.example.modules.user.domain.model.User.Name
import com.example.modules.user.domain.model.User.Password

class PostgresUserRepository(): UserRepository {
    override suspend fun create(user: User): Unit = suspendTransaction{
        UserEntity.new {
            name = user.name.value
            email = user.email.value
            password = user.password.value
        }
    }

    override suspend fun all(): List<Pair<Long,User>> = suspendTransaction {
        UserEntity
            .all()
            .fold(emptyList<Pair<Long,User>>()){ acc, head ->
                try{ acc + Pair(head.id.value.toLong(),head.toModel()) }
                catch (e: UserError){ acc }
            }
    }
}

private fun UserEntity.toModel() = User(
    email = Email(email),
    name = Name(name),
    password = Password(password)
)