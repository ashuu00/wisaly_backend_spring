package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.BlogDto
import com.wisaly.wisaly_kotlin_spring.service.BlogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/blog")
@CrossOrigin("http://localhost:3000", allowCredentials = "true")
class BlogController(private val blogService: BlogService) {

    @GetMapping("/")
    fun returnAll(){
        blogService.fetchAllBlogs()
    }

    @PostMapping("/new")
    fun saveBlog(@RequestAttribute("user_id") user_id:Long?,
                 @RequestBody() blog:BlogDto):ResponseEntity<String>
    {
        return ResponseEntity(blogService.createNewBlog(user_id!!,blog),HttpStatus.CREATED)
    }

    @GetMapping("/fetch")
    fun getAll(){

    }

}