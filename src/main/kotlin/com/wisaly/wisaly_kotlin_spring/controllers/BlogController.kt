package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.BlogDto

import com.wisaly.wisaly_kotlin_spring.service.BlogService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.lang.IllegalStateException

@RestController
@RequestMapping("api/v1/blog")
@CrossOrigin("http://localhost:3000", allowCredentials = "true")
class BlogController(private val blogService: BlogService) {
   private val logger = LoggerFactory.getLogger(BlogController::class.java)
    @GetMapping("/")
    fun returnAll():ResponseEntity<String>{
       return  ResponseEntity.ok(blogService.fetchAllBlogs())
    }

    @PostMapping("/new")
    fun saveBlog(@RequestAttribute("user_id") user_id:Long?,
                 @RequestBody() blog: BlogDto
    ):ResponseEntity<String>
    {
        return ResponseEntity(blogService.createNewBlog(user_id!!,blog),HttpStatus.CREATED)
    }

    @GetMapping("/{blog_link}")
    fun getBlog(@PathVariable("blog_link") blog_link:String,
    @RequestAttribute("user_id") user_id:Long?):ResponseEntity<BlogDto>{
        //return ResponseEntity.ok(blogService.getCompleteBlog)
       try {
           val ans=blogService.getCompleteBlog(blog_link,user_id)
           return ResponseEntity.ok(ans)
       }catch(err:IllegalStateException){
          throw IllegalStateException("No blog can be found")
       }
    }

    @GetMapping("/all")
    fun getAllBlogs(@RequestParam("size")size:Int, @RequestParam("page") page:Int):ResponseEntity<List<BlogDto>>{
        return ResponseEntity.ok(blogService.getAllBlogs(size, page))
    }


}