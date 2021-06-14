package com.wisaly.wisaly_kotlin_spring.dtos.client

import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard

data class BlogClientDto (
    var title: String,
    var description: String,
    var title_pic: String,
    var content: String,
    var cards :List<ExploreCard>?,
        )