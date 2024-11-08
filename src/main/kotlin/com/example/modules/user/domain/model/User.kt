package com.example.modules.user.domain.model

import com.example.modules.user.domain.error.UserError
import kotlinx.serialization.Serializable

@JvmInline @Serializable value class Name(val s: String)
@JvmInline @Serializable value class Email(val s: String)
@JvmInline @Serializable value class Password(val s: String)

@Serializable
data class User(
    val name: Name,
    val email: Email,
    val password: Password,
){companion object}

fun User.Companion.fromStrings(
    name: String,
    email: String,
    password: String,
) = User(
    name.validateName(),
    email.validateEmail(),
    password.validatePassword()
)

private fun String.validateEmail() = Email(this)
    .also{
        if(length>20) throw UserError.InvalidField("Email", "The maximum length is 20.")
        val funIsEmail = {s: String -> "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex().matches(s)}
        if(!funIsEmail(this)) throw UserError.InvalidField("Email", "Not a valid email contaning syntax error.")
    }

private fun String.validateName() = Name(this)
    .also{
        if(length>20) throw UserError.InvalidField("Name", "The maximum length is 20.")
    }

private fun String.validatePassword() = Password(this)
    .also{
        if(length>20) throw UserError.InvalidField("Name", "The maximum length is 20.")
    }