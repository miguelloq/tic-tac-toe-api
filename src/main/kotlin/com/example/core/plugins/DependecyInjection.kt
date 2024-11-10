package com.example.core.plugins

import com.example.core.coreKoinModule
import com.example.core.service.AuthenticationService
import com.example.modules.task.taskKoinModule
import com.example.modules.user.userKoinModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDependecyInjection(){
    install(Koin) {
        slf4jLogger()
        configureKoinModules()
    }
}

fun KoinApplication.configureKoinModules(){
    modules(
        coreKoinModule(),
        taskKoinModule(),
        userKoinModule()
    )
}
