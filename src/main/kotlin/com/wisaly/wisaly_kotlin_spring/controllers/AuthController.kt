package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.dtos.BasicUser
import com.wisaly.wisaly_kotlin_spring.service.UserService
import com.wisaly.wisaly_kotlin_spring.dtos.loginDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("api/v1")
@CrossOrigin("http://localhost:3000", allowCredentials = "true")

class AuthController(private val userService: UserService){

    private val logger = LoggerFactory.getLogger(this.javaClass);
    @PostMapping("/login")
    fun login(
        @RequestBody user: loginDto,
        response: HttpServletResponse): ResponseEntity<BasicUser> {
        println("Inside login body")
        val getUser= userService.manageUser(user);
        val issuer = getUser.id.toString()
        val jwt = Jwts.builder() //creating jwt token here for the user
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7L) )
            .signWith(SignatureAlgorithm.HS256, "shreyanshinishuashu@2805")
            .compact()
        println("jwt token is $jwt")
        val cookie=Cookie("jwt", jwt)
        cookie.isHttpOnly=false
        cookie.secure=true
        cookie.path="/"
        //cookie.isHttpOnly=true
        cookie.maxAge= 60 * 60 * 24 * 7
        response.addCookie(cookie)
        return ResponseEntity.ok(getUser)
    }

    @GetMapping("/logout")
    fun logout(response:HttpServletResponse):ResponseEntity<Any>{
        //delete user cookie after signout
        val delCookie = Cookie("jwt","")
        delCookie.maxAge=0
        delCookie.path="/"
        val deleteDetails = Cookie("activeUser","")
        deleteDetails.maxAge=0
        delCookie.path="/"
        response.addCookie(delCookie);
        response.addCookie(deleteDetails);
        println("User Signed Out")
        return ResponseEntity.ok("Logout was successful")
    }

    @GetMapping("/hello")
    fun helloWorld():ResponseEntity<String>{
        logger.info("Hello World Called!")
        return ResponseEntity.ok("Hello World from Kotlin Spring boot")
    }


}

