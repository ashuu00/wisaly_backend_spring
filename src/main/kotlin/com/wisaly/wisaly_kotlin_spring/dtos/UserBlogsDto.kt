package com.wisaly.wisaly_kotlin_spring.dtos

import java.sql.Timestamp

data class UserBlogsDto(
    val id:Long,
    val title: String,
    val description: String,
    val created: Timestamp,
    val updated: Timestamp,
    val archived:Boolean,
    val display_pic:String,
    val blog_link: String,
    val likes: Int,
    var liked:Boolean? = null
)
