package com.wisaly.wisaly_kotlin_spring.dtos.queries.Blog

import java.sql.Timestamp

interface BlogsAll {
    val id:Long
    val created_at: Timestamp
    val title_pic: String
    val title: String
    val page_link: String
    val description: String
    val content: String
    val full_name:String
    val profile_pic: String
    val user_page_link:String
    val likes: Int?
    var likedByUser:Boolean?
}