package com.example.model

import com.example.modules.task.repository.TaskEntity
import com.example.modules.task.repository.TaskTable
import com.example.modules.user.repository.UserEntity
import com.example.modules.task.repository.daoToModel
import com.example.core.repository.suspendTransaction
import com.example.modules.task.model.Task
import com.example.modules.task.model.Task.Priority
import com.example.modules.task.repository.TaskRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class PostgresTaskRepository : TaskRepository {
    override suspend fun allTasks(): List<Task> = suspendTransaction {
        TaskEntity.all().map(::daoToModel)
    }

    override suspend fun tasksByPriority(priority: Priority): List<Task> = suspendTransaction {
        TaskEntity
            .find { (TaskTable.priority eq priority.toString()) }
            .map(::daoToModel)
    }

    override suspend fun taskByName(name: String): Task? = suspendTransaction {
        TaskEntity
            .find { (TaskTable.name eq name) }
            .limit(1)
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun tasksByUser(userId: Int): List<Task> = suspendTransaction {
        TaskEntity
            .find { (TaskTable.user eq userId) }
            .map(::daoToModel)
    }

    override suspend fun addTask(task: Task, userId: Int): Unit = suspendTransaction {
        val userEntity = UserEntity.findById(userId).let{it ?: throw Exception()}
        TaskEntity.new {
            name = task.name
            description = task.description
            priority = task.priority.toString()
            this@new.user = userEntity
        }
    }

    override suspend fun removeTask(name: String): Boolean = suspendTransaction {
        val rowsDeleted = TaskTable.deleteWhere {
            TaskTable.name eq name
        }
        rowsDeleted == 1
    }
}