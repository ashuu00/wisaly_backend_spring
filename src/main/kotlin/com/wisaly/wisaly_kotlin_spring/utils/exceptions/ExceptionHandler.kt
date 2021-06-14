package com.wisaly.wisaly_kotlin_spring.utils.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception
import java.lang.IllegalStateException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(IllegalStateException::class)
    fun illegalStateHandler(exception: Exception):ResponseEntity<ApiError>{
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(UserNotFound::class)
    fun userNotFoundHandler(exception: Exception):ResponseEntity<ApiError>{
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }
}