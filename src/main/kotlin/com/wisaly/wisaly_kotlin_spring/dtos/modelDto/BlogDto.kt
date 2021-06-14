package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Tag
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant

data class BlogDto(
    var id:Long = -1,
    var created: Timestamp = Timestamp(Instant.now().toEpochMilli()),
    var updated: Timestamp =  Timestamp(Instant.now().toEpochMilli()),
    var title: String,
    var description: String,
    var title_pic: String,
    var archived: Boolean = false,
    var draft: Boolean = false,
    var content: String,
    var cards :MutableList<ExploreCard> = mutableListOf(),
    var author: User? = null,
    var categories: List<String> = mutableListOf(),
    var tags: List<String> = mutableListOf(),

)
