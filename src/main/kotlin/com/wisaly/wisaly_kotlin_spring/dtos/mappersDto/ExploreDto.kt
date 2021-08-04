package com.wisaly.wisaly_kotlin_spring.dtos.mappersDto


//return the ids of all the
//initially, all ids in string, then return the respective urls and other types
data class ExploreDto (
    var id:Long?=-1,
    var title: String,
    var description: String,
    var archived: Boolean=false,
    var videos: MutableList<String> = mutableListOf(),
    var images: MutableList<String> = mutableListOf(),
    var categories: List<String> = mutableListOf(),
    var blogs:List<String> = listOf(),
    var tags: List<String> = listOf(),
)