package com.wisaly.wisaly_kotlin_spring.config



import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter



@Configuration
@EnableWebSecurity
class WebConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity){
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/**")
            .permitAll()
            .anyRequest()
            .authenticated()
        http.cors()
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