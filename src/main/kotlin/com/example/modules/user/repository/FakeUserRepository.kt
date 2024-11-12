package com.example.modules.user.repository

import com.example.modules.user.domain.model.User

class FakeUserRepository: UserRepository {
    private val users = mutableListOf<User>()

    override suspend fun create(user: User) {
        users.add(user)
    }

    override suspend fun all(): List<Pair<Long, User>> =
        users
            .withIndex()
            .map{(idx,user) -> idx.toLong() to user}

    override suspend fun findByEmail(email: User.Email): Pair<Long, User>? =
        all()
            .filter {(_,user) -> user.email == email }
            .firstOrNull()

    override suspend fun findById(id: Long): User? =
        all()
            .filter { (idUser,_)-> idUser == id }
            .firstOrNull()
            ?.second
}