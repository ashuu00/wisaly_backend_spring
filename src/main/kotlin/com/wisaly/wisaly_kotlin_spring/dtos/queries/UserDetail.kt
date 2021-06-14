package com.wisaly.wisaly_kotlin_spring.dtos.queries

import com.wisaly.wisaly_kotlin_spring.dtos.enums.Genders
import java.sql.Timestamp
import java.time.Instant

interface UserDetail {
    val id: Long
    val created_at: Timestamp
    var updated_at: Timestamp
    var email:String
    val first_name:String
    val last_name:String
    var display_pic:String
    var phone_no:String
    var gender: Genders
    var profile_complete:Boolean
    var roles: String
}