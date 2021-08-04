package com.wisaly.wisaly_kotlin_spring.service

import com.wisaly.wisaly_kotlin_spring.models.user.User
import com.wisaly.wisaly_kotlin_spring.repository.UserRepository
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeAll
import java.sql.Timestamp
import java.time.Instant

internal class UserWisalyServiceMapperTest {

    val userRepo = mockk<UserRepository>()

    companion object {
        @BeforeAll
        @JvmStatic
        fun setUp() {
//            val user = User(
//                first_name = "Ashutosh",
//                last_name = "Anurag",
//                email = "ashu28anurag@gmail.com",
//                roles = "User",
//                id = 1,
//                blogs = mutableListOf(),
//                videos = mutableListOf(),
//                comments = mutableListOf(),
//                created_at = Timestamp(Instant.now().toEpochMilli()),
//                updated_at = Timestamp(122323243L),
//                explore_cards = mutableListOf(),
//                followers = mutableListOf(),
//                following = mutableListOf(),
//            )
        }
    }


    @AfterEach
    fun tearDown() {
    }

    @Test
    fun saveUser() {
    }

    @Test
    fun manageUser() {
    }

    @Test
    fun getUserDetails() {
    }

    @Test
    fun loginUser() {
    }

    @Test
    fun getUserRepository() {
    }
}