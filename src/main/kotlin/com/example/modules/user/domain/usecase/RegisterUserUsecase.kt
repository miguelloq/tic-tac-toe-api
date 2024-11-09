package com.example.modules.user.domain.usecase

import com.example.modules.user.domain.model.User
import com.example.modules.user.domain.model.User.Email
import com.example.modules.user.domain.model.User.Name
import com.example.modules.user.domain.model.User.Password
import com.example.modules.user.repository.UserRepository
import com.example.modules.user.route.RegisterUserDto

class RegisterUserUsecase(val userRepo: UserRepository) {
    suspend operator fun invoke(dto: RegisterUserDto){
        val model = dto.toModel()
        userRepo.create(model)
    }
}

private fun RegisterUserDto.toModel() = User(
    name = Name(name),
    email = Email(email),
    password = Password(password)
)