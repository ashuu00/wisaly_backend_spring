package com.wisaly.wisaly_kotlin_spring.security

import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import org.springframework.boot.web.servlet.server.Session
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.util.WebUtils
import java.util.*
import java.util.logging.Logger
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//@Component
//@Order(1)
//class JwtFilter: Filter {
//    var logger:Logger= Logger.getLogger("info")
//    //check for all protected routes, like users, and admin routes
//    //we also need to check for roles of the value that we get for admin and developer routes
//    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
//      // val myCookie:List<Cookie?> = (request as HttpServletRequest).cookies.filter {it.name=="Jwt"}
//       val myHeader = (request as HttpServletRequest).getHeader("Authorization").split(" ")[1] as String?
//           ?: throw IllegalAccessError("The user is not authorized to view the website")
//        val user = Jwts.parser().setSigningKey("shreyanshinishuashu@2805").parseClaimsJws(myHeader).body
//        println("Issuer is ${user.issuer}")
//       request.setAttribute("user_id", user.issuer)
//        chain?.doFilter(request,response)
//    }
//
//}