package com.wisaly.wisaly_kotlin_spring.utils.exceptions

import org.springframework.http.HttpStatus
import java.sql.Timestamp


data class ApiError (
    private val _message: String?,
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
    val code: Int = status.value(),
    val timestamp: Timestamp = Timestamp(System.currentTimeMillis())
){  //passing message from private to public methods using kotlin
    val message: String
    get() = _message?: "Something went wrong"
}