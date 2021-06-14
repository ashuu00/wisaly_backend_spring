package com.wisaly.wisaly_kotlin_spring.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class WebConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity){
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/**")
            .permitAll()
            .anyRequest()
            .authenticated()
    }

//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource? {
//        val configuration = CorsConfiguration()
//        configuration.allowedOrigins = listOf("https://www.wisaly.com", "http://localhost:3000")
//        configuration.allowedMethods = listOf("GET", "POST","PUT","DELETE")
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }


}