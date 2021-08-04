package com.wisaly.wisaly_kotlin_spring.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//test case for login and see if jwt is working
@Component
class CheckTokenFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")?:"null"

        println("Header got is $authHeader")
        if (!authHeader.contains("null")&&authHeader.split(" ")[1]!="undefined" ) {
            try {
                val user = Jwts.parser().setSigningKey("shreyanshinishuashu@2805").parseClaimsJws(authHeader.split(" ")[1]).body
                println("Issuer is ${user.issuer}")
                request.setAttribute("user_id", user.issuer.toLong()) //the userid is still string, convert to int
            } catch (error: SignatureException) {
                response.sendError(HttpStatus.FORBIDDEN.value(),"Jwt value cannot be trusted")
                //throw IllegalAccessException("Jwt key is not defined")
            }
        }
        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path: String = request.requestURI
        return path.contains("/login")
    }
}