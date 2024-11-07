package com.example.modules.user

import com.example.modules.user.repository.PostgresUserRepository
import com.example.modules.user.repository.UserRepository
import org.koin.dsl.module

val userKoinModule = module{
    single<UserRepository>{ PostgresUserRepository() }
}