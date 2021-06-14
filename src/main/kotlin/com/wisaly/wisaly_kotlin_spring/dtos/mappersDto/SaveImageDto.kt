package com.wisaly.wisaly_kotlin_spring.dtos.mappersDto

import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.User

data class SaveImageDto(
    var photo_id:Long?=-1,
    var author: User,
    var img_link:String,
    var archived: Boolean=false,
    var cards: MutableList<ExploreCard>?= mutableListOf(),
)
