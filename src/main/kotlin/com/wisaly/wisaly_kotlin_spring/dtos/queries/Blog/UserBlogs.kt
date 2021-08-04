package com.wisaly.wisaly_kotlin_spring.dtos.queries.Blog

import java.sql.Timestamp

interface UserBlogs {
    val id:Long
    val created_at: Timestamp
    val updated_at: Timestamp
    val archived: Boolean
    val title_pic: String
    val title: String
    val page_link: String
    val description: String
    val likes: Int
    var likedByUser:Boolean?
}