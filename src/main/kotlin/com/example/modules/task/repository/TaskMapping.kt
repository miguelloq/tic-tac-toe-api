package com.example.modules.task.repository

import com.example.modules.user.repository.UserEntity
import com.example.modules.user.repository.UserTable
import com.example.modules.task.model.Task
import com.example.modules.task.model.Task.Priority
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


object TaskTable : IntIdTable("task") {
    val name = varchar("name", 50)
    val description = varchar("description", 50)
    val priority = varchar("priority", 50)
    val user = reference("user_id", UserTable)
}

class TaskEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TaskEntity>(TaskTable)

    var name by TaskTable.name
    var description by TaskTable.description
    var priority by TaskTable.priority
    var user by UserEntity.Companion referencedOn TaskTable.user
}

fun daoToModel(dao: TaskEntity) = Task(
    dao.name,
    dao.description,
    Priority.valueOf(dao.priority)
)

