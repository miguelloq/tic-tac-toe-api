package com.example.modules.user.domain.usecase

import com.example.modules.user.domain.error.UserError
import com.example.modules.user.domain.model.User
import com.example.modules.user.domain.model.User.Email
import com.example.modules.user.domain.model.User.Name
import com.example.modules.user.domain.model.User.Password
import com.example.modules.user.repository.UserRepository
import kotlinx.serialization.Serializable

class RegisterUserUsecase(val userRepo: UserRepository) {
    suspend operator fun invoke(dto: RegisterUserDto){
        val model = dto.toModel()
        val isEmailAlreadyInUse = userRepo.findByEmail(model.email) != null
        if(isEmailAlreadyInUse) throw UserError.EmailAlreadyRegistered
        userRepo.create(model)
    }
}

@Serializable
data class RegisterUserDto(
    val name: String,
    val email: String,
    val password: String
)

private fun RegisterUserDto.toModel() = User(
    name = Name(name),
    email = Email(email),
    password = Password(password)
)