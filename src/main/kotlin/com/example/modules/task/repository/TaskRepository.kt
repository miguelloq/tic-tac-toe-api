package com.example.modules.task.repository

import com.example.modules.task.model.Task

interface TaskRepository {
    suspend fun allTasks(): List<Task>
    suspend fun tasksByPriority(priority: Task.Priority): List<Task>
    suspend fun tasksByUser(userId: Int): List<Task>
    suspend fun taskByName(name: String): Task?
    suspend fun addTask(task: Task, userId: Int)
    suspend fun removeTask(name: String): Boolean
}