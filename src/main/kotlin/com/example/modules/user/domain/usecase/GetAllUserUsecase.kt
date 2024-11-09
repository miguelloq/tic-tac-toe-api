package com.example.modules.user.domain.usecase

import com.example.modules.user.domain.model.User
import com.example.modules.user.repository.UserRepository

class GetAllUserUsecase(val userRepo: UserRepository) {
    suspend operator fun invoke(): List<Pair<Long,User>> =
        userRepo.all()
}