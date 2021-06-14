package com.wisaly.wisaly_kotlin_spring.dtos.mappersDto

import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Image
import com.wisaly.wisaly_kotlin_spring.models.Tag
import com.wisaly.wisaly_kotlin_spring.models.Video
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.user.User


//return the ids of all the
data class ExploreDto (
    var card_id:Long?=-1,
    var card_title: String,
    var description: String,
    var archived: Boolean=false,
    var entityBlogs: MutableList<Blog> = mutableListOf(),
    var videos: MutableList<String> = mutableListOf(),
    var images: MutableList<String> = mutableListOf(),
    var entityCategories: MutableList<Category> = mutableListOf(),
    var entityImages: MutableList<Image> = mutableListOf(),
    var entityVideos: MutableList<Video> = mutableListOf(),
    var entityTags: MutableList<Tag> = mutableListOf(),
    var blogIds:List<Long> = listOf(),
    var categories: List<String> = listOf(),
    var tags: List<String> = listOf(),
    var author: User?=null
)