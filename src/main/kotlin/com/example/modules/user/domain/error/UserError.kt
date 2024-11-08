package com.example.modules.user.domain.error

sealed class UserError(override val message: String): Exception(message) {
    data class InvalidField(val fieldName: String, val reason: String):
        UserError("The $fieldName is not valid because $reason.")

    data object Unknown: UserError("Unknown Error")
}