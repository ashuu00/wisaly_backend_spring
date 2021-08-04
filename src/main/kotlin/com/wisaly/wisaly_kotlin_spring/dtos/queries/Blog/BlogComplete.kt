package com.wisaly.wisaly_kotlin_spring.dtos.queries.Blog

import java.sql.Timestamp

interface BlogComplete {
    val id:Long
    val created_at: Timestamp
    val title_pic: String
    val title: String
    val page_link: String
    val description: String
    val full_name: String?
    val user_id:Long?
}