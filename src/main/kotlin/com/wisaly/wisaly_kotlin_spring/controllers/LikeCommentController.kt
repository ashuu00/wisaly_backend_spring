package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.service.LikeCommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/")
@CrossOrigin("http://localhost:3000", allowCredentials = "true")
class LikeCommentController(private val likeCommentService: LikeCommentService) {

    @PostMapping("v1/like/blog")
    fun likeBlog(@RequestAttribute("user_id") user_id:Long,
                 @RequestParam("blog_id") blog_id: Long):ResponseEntity<String>{
        //add checks if user and blogs exist
        return ResponseEntity(likeCommentService.likeBlog(user_id,blog_id),HttpStatus.CREATED)
    }
}