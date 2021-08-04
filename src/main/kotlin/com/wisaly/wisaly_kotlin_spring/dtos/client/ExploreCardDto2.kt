package com.wisaly.wisaly_kotlin_spring.dtos.client

data class ExploreCardDto2(
    var title: String,
    var description: String,
    var archived: Boolean,
    var blogs:List<Long> = mutableListOf(),
    var photos: List<Long> = mutableListOf(),
    var videos: List<Long> = mutableListOf(),
    var categories:List<String> = mutableListOf(),
    var tags:List<String> = mutableListOf(),
    var user_id: Long = 0
)
