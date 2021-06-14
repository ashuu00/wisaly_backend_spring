package com.wisaly.wisaly_kotlin_spring.dtos.queries

interface UserBlogs {
    val id:Long
    val blog_title: String
    val blog_description: String
    val likes: Int
}