package com.wisaly.wisaly_kotlin_spring.controllers
import com.wisaly.wisaly_kotlin_spring.dtos.client.feedbackDto
import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.UserDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.BasicUserDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.SuggestedUsers
import com.wisaly.wisaly_kotlin_spring.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/user")
class UserController(val userService: UserService) {



    @GetMapping("account")
    fun getUserInfo(@RequestAttribute("user_id") user_id:Long?, @RequestParam("username") username:String?): ResponseEntity<String> {
        println("Username is $username")
        return ResponseEntity.ok(userService.getUserDetails(user_id?:-1,username))
    }

    @GetMapping("/basic")
    fun basicDetails(@RequestAttribute("user_id") user_id: Long?):ResponseEntity<BasicUserDto>{
        if(user_id == null) throw IllegalStateException("No user found")
        return ResponseEntity.ok(userService.getBasicDetails(user_id))
    }


    @PutMapping("/update")
    fun updateUser(@RequestAttribute("user_id") user_id:Long?, @RequestBody details:UserDto):ResponseEntity<UserDto>{
        return ResponseEntity(userService.updateUser(user_id, details), HttpStatus.ACCEPTED)
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

    /** initially, we are just adding the follow button */
    @PostMapping("/add-relation")
    fun followUser(@RequestAttribute("user_id") user_id:Long?, @RequestParam("lead_user_id") lead_user_id:Long? ):ResponseEntity<String>{
        if(user_id==null||lead_user_id==null) throw IllegalStateException("Ids are not valid")
        return ResponseEntity(userService.setRelationship(user_id, lead_user_id),HttpStatus.CREATED)
    }

    @GetMapping("/suggest")
    fun getSuggested(@RequestAttribute("user_id") user_id: Long?):ResponseEntity<List<BasicUserDto>>{
        val allSuggestedUsers = userService.suggestusers(user_id)
        return ResponseEntity(allSuggestedUsers,HttpStatus.ACCEPTED)
    }

    class myEmail(){
       lateinit var email:String
}
    @GetMapping("/email/{email}")
    fun getEmail(@RequestBody() email:myEmail):ResponseEntity<String>{
        println("got email ${email.email}")
        return ResponseEntity.ok(userService.findEmail(email.email))
    }

    @GetMapping("/search")
    fun getSearchRes(@RequestParam query:String?):ResponseEntity<MutableList<BasicUserDto>>{
        return ResponseEntity.ok(userService.searchUser(query?:""))
    }

    @PostMapping("/feedback")
    fun addFeedback(@RequestAttribute("user_id") user_id:Long?, @RequestBody feedback: feedbackDto):ResponseEntity<String>{
        if(user_id==null){ return ResponseEntity.ok("No user found")}
        return ResponseEntity(userService.addFeedBack(user_id, feedback),HttpStatus.CREATED)
    }
    @GetMapping("/following")
    fun getFollowing(@RequestAttribute("user_id") user_id:Long?):ResponseEntity<String>{
        return ResponseEntity(userService.getFollowing(user_id!!),HttpStatus.ACCEPTED)
    }

    /** test this **/
    @GetMapping("/follower")
    fun getFollowers(@RequestAttribute("user_id") user_id: Long?):ResponseEntity<String>{
        if(user_id==null)throw IllegalStateException("User id must be provided")
        return ResponseEntity.ok(userService.getFollowing(user_id))
    }
}