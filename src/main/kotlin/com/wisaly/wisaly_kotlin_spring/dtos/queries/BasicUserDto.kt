package com.wisaly.wisaly_kotlin_spring.dtos.queries

import com.wisaly.wisaly_kotlin_spring.dtos.enums.Genders
import java.sql.Timestamp
import java.time.Instant
class BasicUserDto (
    val id: Long =-1,
    var created: String ="",
    val first_name:String,
    val last_name:String,
    var username:String,
    val about: String? = "",
    val followers: Int? = null,
    val mutual: Int? = null,
    var display_pic:String = "",
    var following:Boolean? = null,
)