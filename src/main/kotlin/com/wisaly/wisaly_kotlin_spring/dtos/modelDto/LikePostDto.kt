package com.wisaly.wisaly_kotlin_spring.dtos.modelDto
import com.wisaly.wisaly_kotlin_spring.dtos.enums.LikeType
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.User


data class LikePostDto(
    val id:Long = -1,
    var likeType: LikeType,
    var post: ExploreCard,
    var author: User

)
