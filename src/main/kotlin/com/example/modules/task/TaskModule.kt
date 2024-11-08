package com.example.modules.task

import com.example.model.PostgresTaskRepository
import com.example.modules.task.repository.TaskRepository
import com.example.modules.task.route.taskRoute
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.koin

fun Application.taskModule(){
    koin{ modules(
        module{
            single<TaskRepository>{ PostgresTaskRepository() }
        }
    )}

    val taskRepo by inject<TaskRepository>()
    routing{ taskRoute(taskRepo) }

}