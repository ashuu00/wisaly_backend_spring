package com.wisaly.wisaly_kotlin_spring.dtos.queries

import java.sql.Timestamp

interface AllProducts {
    val id:Long
    val title:String
    val cost: Int
    val discount: Int
    val buy_limit: Int
    val pictures: Int
    val created_at: Timestamp
    val page_link: String
}