package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

import java.sql.Timestamp
import java.time.Instant


data class RatingDto (

    var id:Long = 0,
    var created_at: Timestamp = Timestamp.from(Instant.now()),

    var likes: Int = 0,

    var rating:Int = 0,

    var content: String,
        )