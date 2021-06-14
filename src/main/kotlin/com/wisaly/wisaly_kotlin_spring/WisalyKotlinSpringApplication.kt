package com.wisaly.wisaly_kotlin_spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import javax.imageio.ImageIO


@SpringBootApplication
@EnableTransactionManagement
class WisalyKotlinSpringApplication

fun main(args: Array<String>) {

    for(item in ImageIO.getReaderMIMETypes()){
        println("Images type are $item")
    }

    runApplication<WisalyKotlinSpringApplication>(*args)
}



