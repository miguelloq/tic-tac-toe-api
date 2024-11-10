package com.example.core

import com.example.core.service.AuthenticationService
import org.koin.core.KoinApplication
import org.koin.dsl.module


fun KoinApplication.coreKoinModule() = module{
    single<AuthenticationService>{ AuthenticationService() }
}