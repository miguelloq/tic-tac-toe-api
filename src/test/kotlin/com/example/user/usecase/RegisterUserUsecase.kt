package com.example.user.usecase

import com.example.modules.user.domain.error.UserError.InvalidField
import com.example.modules.user.domain.usecase.RegisterUserDto
import com.example.modules.user.domain.usecase.RegisterUserUsecase
import com.example.modules.user.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class RegisterUserUsecaseTest {
    @MockK private lateinit var userRepository: UserRepository
    @InjectMockKs lateinit var usecase: RegisterUserUsecase

    @BeforeEach
    fun setUp(){
        MockKAnnotations.init(this)
        coEvery {userRepository.create(any()) } just Runs
    }

    @Test
    fun `register is successful`(){
        val dto = RegisterUserDto(
            name = "Miguel",
            email = "miguel@gmail.com",
            password = "12345"
        )
        runBlocking { assertEquals(usecase(dto), Unit) }
    }

    @Test
    fun `register with invalid email`(){
        val dto = RegisterUserDto(
            name = "Miguel",
            email = "miguelgmail.com",
            password = "12345"
        )
        runBlocking { assertThrows<InvalidField> { usecase(dto) } }
    }
}
