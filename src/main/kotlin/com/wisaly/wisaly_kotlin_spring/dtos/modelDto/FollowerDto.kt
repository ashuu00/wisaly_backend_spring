package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp

data class FollowerDto (
    val id: Long = -1,
    val created_at: Timestamp,
    var updated_at: Timestamp,
    var leader_user: User,
    var following_user: User
        )