package com.example.modules.task

import com.example.model.PostgresTaskRepository
import com.example.modules.task.repository.TaskRepository
import org.koin.dsl.module

val taskKoinModule = module{
    single<TaskRepository>{ PostgresTaskRepository() }
}