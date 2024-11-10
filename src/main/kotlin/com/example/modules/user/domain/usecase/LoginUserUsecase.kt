package com.example.modules.user.domain.usecase

import com.example.core.model.TokenConfig
import com.example.modules.user.domain.error.UserError
import com.example.modules.user.domain.model.User.Email
import com.example.modules.user.domain.model.User.Password
import com.example.modules.user.repository.UserRepository
import com.example.modules.user.service.CreateTokenService
import kotlinx.serialization.Serializable

class LoginUserUsecase(
    val userRepo: UserRepository,
    val createTokenService: CreateTokenService
) {
    suspend operator fun invoke(tokenConfig: TokenConfig,dto: LoginRequestDto): String{
        val (email, password) = dto.toDomain()

        val (id,repoUser) = userRepo.findByEmail(email)
            ?: throw UserError.NotFindedInDatabase

        if(repoUser.password.value.also(::println) != password.value.also(::println))
            throw UserError.WrongPassword

        return createTokenService.getToken(id,tokenConfig)
    }
}

@Serializable
data class LoginRequestDto(
    val email: String,
    val password: String
)

private fun LoginRequestDto.toDomain() = Pair(
    Email(email),
    Password(password)
)