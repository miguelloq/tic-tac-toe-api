package com.example.modules.user.repository

import com.example.modules.user.domain.model.User
import com.example.modules.user.domain.model.User.Email
import com.example.modules.user.domain.model.User.Name
import com.example.modules.user.domain.model.User.Password
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable: IntIdTable("users"){
    val name = varchar("name",20)
    val password = varchar("password",20)
    val email = varchar("email",40)
}

class UserEntity(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<UserEntity>(UserTable)

    var name by UserTable.name
    var password by UserTable.password
    var email by UserTable.email
}