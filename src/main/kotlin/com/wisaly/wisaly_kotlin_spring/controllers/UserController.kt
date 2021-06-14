package com.wisaly.wisaly_kotlin_spring.controllers
import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.UserDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.SuggestedUsers
import com.wisaly.wisaly_kotlin_spring.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/user")
class UserController(val userService: UserService) {

    @GetMapping("account")
    fun getUserInfo(@RequestAttribute("user_id") user_id:Long?): ResponseEntity<String> {
        return ResponseEntity.ok(userService.getUserDetails(user_id?:-1))
    }

    @PostMapping("/updateUser")
    fun updateUser(){

    }

    @GetMapping("/blogs")
    fun getBlogs(@RequestAttribute("user_id") user_id:Long?):ResponseEntity<String>{
        return ResponseEntity(userService.getBlogs(user_id!!),HttpStatus.ACCEPTED)
    }

    @GetMapping("/")
    fun sendMsg(@RequestAttribute("user_id") user_id: Long?): String{
        return "Got here $user_id"
    }

    @PostMapping("/category")
    fun manageCategories(@RequestBody categories: List<String>): String{
        return "Get it"
    }

    @PostMapping("/follow")
    fun followUser(@RequestAttribute("user_id") user_id:Long?, @RequestBody() lead_user_id:Long ):ResponseEntity<String>{

        return ResponseEntity(userService.followUser(user_id?:-1, lead_user_id),HttpStatus.CREATED)
    }

    @GetMapping("/suggested")
    fun getSuggested():ResponseEntity<List<SuggestedUsers>>{
        val allSuggestedUsers = userService.suggestusers()
        return ResponseEntity(allSuggestedUsers,HttpStatus.ACCEPTED)
    }

}