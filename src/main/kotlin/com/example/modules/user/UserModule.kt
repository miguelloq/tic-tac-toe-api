package com.example.modules.user

import com.example.modules.user.domain.usecase.GetAllUserUsecase
import com.example.modules.user.domain.usecase.LoginUserUsecase
import com.example.modules.user.domain.usecase.RegisterUserUsecase
import com.example.modules.user.repository.FakeUserRepository
import com.example.modules.user.repository.PostgresUserRepository
import com.example.modules.user.repository.UserRepository
import com.example.modules.user.service.CreateTokenService
import com.example.modules.user.service.CreateTokenServiceImpl
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.userKoinModule() = module{
    single<UserRepository>{ PostgresUserRepository() }
    //single<UserRepository>{ FakeUserRepository() }
    single<CreateTokenService>{ CreateTokenServiceImpl() }
    single{ RegisterUserUsecase(get()) }
    single{ GetAllUserUsecase(get()) }
    single{ LoginUserUsecase(get(),get()) }
}