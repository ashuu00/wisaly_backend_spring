package com.wisaly.wisaly_kotlin_spring.dtos.queries.explore_card

import java.sql.Timestamp

interface ExploreBlogs {
    val id: Long
    val title:String
    val picture:String
    val description:String
    val link: String
    val created: Timestamp
}