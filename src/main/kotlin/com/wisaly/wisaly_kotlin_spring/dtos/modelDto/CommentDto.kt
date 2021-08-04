package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

data class CommentDto(
    var id: Long =-1,
    var content: String = "",
    var authorName: String ="",
    var authorId: Long? = null,
    var authorUsername:String = "",
    var authorPic: String ="",
    var created: String ="",
    var exploreCard: Long = -1,
    var blog: Long =-1
)
