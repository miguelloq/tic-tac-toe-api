package com.example.modules.user.repository

import com.example.modules.user.model.User
import com.example.modules.task.repository.TaskEntity
import com.example.modules.task.repository.TaskTable
import com.example.modules.task.repository.daoToModel
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable: IntIdTable("users"){
    val name = varchar("name",20)
    val password = varchar("password",20)
}


open class UserEntity(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<UserEntity>(UserTable)

    var name by UserTable.name
    var password by UserTable.password
}

fun UserEntity.toModel(withoutTasks: Boolean = false) = User(
    name,
    password,
)