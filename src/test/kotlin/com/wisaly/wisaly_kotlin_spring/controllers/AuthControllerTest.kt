package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.service.UserService
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
internal class AuthControllerTest{
    var userService= mockk<UserService>()
    @Autowired
    lateinit var mockMvc:MockMvc

    @Test
    fun `initialize mockMvc`(){

    }


}