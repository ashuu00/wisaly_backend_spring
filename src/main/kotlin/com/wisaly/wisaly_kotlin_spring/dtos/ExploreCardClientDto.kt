package com.wisaly.wisaly_kotlin_spring.dtos

data class ExploreCardClientDto(
    var card_id:Long?,
    var card_title: String,
    var description: String,
    var archived: Boolean,
    var blogs:List<String?>,
    var photos: List<String>,
    var videos: List<String?>,
    var categories:List<String>,
    var user_id: Long?
)
