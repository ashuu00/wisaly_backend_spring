package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Image
import com.wisaly.wisaly_kotlin_spring.models.Keyword
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant

data class ExploreCardDto(
    var id: Long,
    var created_at: Timestamp? = Timestamp.from(Instant.now()),
    var updated_at: Timestamp? =  Timestamp.from(Instant.now()),
    var created: String = "",
    var categories: List<String> = mutableListOf(),//each card should have a category,
    var authorUsername: String ="",
    var authorFullName: String ="",
    var authorBio: String = "",
    var authorPic: String = "",
    var images:List<String> = mutableListOf(),
    var blogs:List<Long> = mutableListOf(),
    var comments:List<CommentDto> = mutableListOf(),
    var archived: Boolean = false,
    var title:String = "",
    var link:String ="",
    var description: String = "",
    var keywords: List<String> = mutableListOf(),
    var likes:Int = 0,
    var likedByUser:Boolean = false
)
