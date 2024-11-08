package com.example.modules.user.domain.usecase

import com.example.modules.user.domain.model.User
import com.example.modules.user.domain.model.fromStrings
import com.example.modules.user.repository.RegisterUserDto
import com.example.modules.user.repository.UserRepository

class RegisterUserUsecase(val userRepo: UserRepository) {
    suspend operator fun invoke(dto: RegisterUserDto){
        val model = dto.toModel()
        userRepo.create(model)
    }
}

private fun RegisterUserDto.toModel() = User.fromStrings(
    name,
    email,
    password
)