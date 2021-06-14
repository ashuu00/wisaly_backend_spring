package com.wisaly.wisaly_kotlin_spring.dtos.queries

import java.sql.Timestamp

interface SuggestedUsers {
    val first_name:String
    val  last_name:String
    val created_at:Timestamp
    val email:String
    val display_pic:String

}