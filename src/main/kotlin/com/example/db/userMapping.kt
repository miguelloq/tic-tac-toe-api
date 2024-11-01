package com.example.db

import com.example.model.Task
import com.example.model.User
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable: IntIdTable("users"){
    val name = varchar("name",20)
    val password = varchar("password",20)
}

sealed interface AbstractUserEntity

open class UserEntity(id: EntityID<Int>): IntEntity(id), AbstractUserEntity{
    companion object : IntEntityClass<UserEntity>(UserTable)

    var name by UserTable.name
    var password by UserTable.password
    val tasks by TaskEntity referrersOn TaskTable.user
}

fun UserEntity.toModel(withoutTasks: Boolean = false) = User(
    name,
    password,
    if(withoutTasks) emptyList() else tasks.map { daoToModel(it) }
)