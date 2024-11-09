package com.example.modules.user

import com.example.modules.user.domain.usecase.GetAllUserUsecase
import com.example.modules.user.domain.usecase.RegisterUserUsecase
import com.example.modules.user.repository.PostgresUserRepository
import com.example.modules.user.repository.UserRepository
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.userKoinModule() = module{
    single<UserRepository>{ PostgresUserRepository() }
    single{ RegisterUserUsecase(get()) }
    single{ GetAllUserUsecase(get()) }
}