package com.example.core.repository

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object CoreUserTable: IntIdTable("users"){}

class CoreUserEntity(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<CoreUserEntity>(CoreUserTable)
}