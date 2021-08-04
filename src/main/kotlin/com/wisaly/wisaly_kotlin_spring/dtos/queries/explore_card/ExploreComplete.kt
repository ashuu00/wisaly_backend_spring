package com.wisaly.wisaly_kotlin_spring.dtos.queries.explore_card

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.ExploreCardDto

data class ExploreComplete(
    val details: ExploreCardDto,
    val blogs: List<ExploreBlogs> = mutableListOf(),
    val images: List<ExploreMedia> = mutableListOf(),
    val videos: List<ExploreMedia> = mutableListOf(),
    val likes: List<ExploreLikes> = mutableListOf(),
    var likedByUser: Boolean = false
)
