package com.example.modules.user

import com.example.modules.task.repository.TaskRepository
import com.example.modules.user.repository.PostgresUserRepository
import com.example.modules.user.repository.UserRepository
import com.example.modules.user.route.usersRoute
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.koin

fun Application.userModule(){
    koin{modules(
        module{
            single<UserRepository>{ PostgresUserRepository() }
        }
    )}

    val userRepo by inject<UserRepository>()
    routing{ usersRoute(userRepo) }
}