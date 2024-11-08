package com.example.modules.task

import com.example.model.PostgresTaskRepository
import com.example.modules.task.repository.TaskRepository
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.taskKoinModule() = module{
    single<TaskRepository>{ PostgresTaskRepository() }
}