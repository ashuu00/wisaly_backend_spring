package com.wisaly.wisaly_kotlin_spring.config


import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class CorsConfig:WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
       registry.addMapping("/**")
            .allowedHeaders("*")
            .allowedMethods("PUT", "DELETE","GET", "POST","OPTIONS")
            .allowedOrigins("http://localhost:3000")
           .maxAge(360000)
            .allowCredentials(true) //in order to get the cookies, allowed it
    }
}