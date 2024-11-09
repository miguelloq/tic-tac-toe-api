package com.example.modules.user.domain.model

import com.example.modules.user.domain.error.UserError
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: Name,
    val email: Email,
    val password: Password,
){
    @JvmInline @Serializable value class Password(val value: String){
        init{
            if(value.length>20) throw UserError.InvalidField("Name", "The maximum length is 20.")
        }
    }

    @JvmInline @Serializable value class Name(val value: String){
        init{
            if(value.length>20)
                throw UserError.InvalidField("Name", "maximum length is 20.")
            if(value.isEmpty())
                throw UserError.InvalidField("Name", "cannot be blank.")
        }
    }

    @JvmInline @Serializable value class Email(val value: String){
        init{
            if(value.length>40) throw UserError.InvalidField("Email", "The maximum length is 20.")
            val funIsEmail = {s: String -> "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex().matches(s)}
            if(!funIsEmail(value)) throw UserError.InvalidField("Email", "Not a valid email contaning syntax error.")
        }
    }
}