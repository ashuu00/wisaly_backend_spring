package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.CommentDto
import com.wisaly.wisaly_kotlin_spring.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/comment")
@CrossOrigin("http://localhost:3000",)
class CommentController(private val commentService: CommentService) {
    @PostMapping("explore/add")
    fun addCommentcard(@RequestBody comment: CommentDto, @RequestAttribute("user_id") userId:Long): ResponseEntity<String>{
        val res = commentService.createCommentCard(userId,comment)
        return ResponseEntity(res,HttpStatus.CREATED)
    }
}