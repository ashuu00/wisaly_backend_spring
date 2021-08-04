package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

import com.wisaly.wisaly_kotlin_spring.models.RichTextContent
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
    var contentEntity: RichTextContent?=null,
    var blog_link: String?=null,
    var cards :MutableList<Long> = mutableListOf(),
    var authorName: String? = null,
    var authorId: Long = 0,
    var authorPage: String = "",
    var authorPic: String = "",
    var authorDescription:String = "",
    var owner:Boolean=false,
    var likes: Int = 0,
    var likedByUser: Boolean = false,
    var categories: List<String> = mutableListOf(),
    var tags: List<String> = mutableListOf(),


)
