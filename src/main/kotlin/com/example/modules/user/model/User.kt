package com.example.modules.user.model

import com.example.modules.task.model.Task
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val password: String,
    val tasks: List<Task>
)
