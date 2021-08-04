package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

import com.wisaly.wisaly_kotlin_spring.dtos.enums.Genders
import com.wisaly.wisaly_kotlin_spring.models.*
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.CommentPost
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class UserDto(
    val id: Long = -1,
    val created_at: Timestamp = Timestamp(Instant.now().toEpochMilli()),
    var updated_at: Timestamp = Timestamp(Instant.now().toEpochMilli()),
    var email:String ="undefined",
    val username:String =email.split("@")[0],
    val first_name:String = "",
    val last_name:String = "",
    var display_pic:String = "",
    var phone_no:String = "",
    var about:String ="",
    var city:String = "",
    var gender: Genders = Genders.UNDEFINED,
    var profile_complete: Boolean = false,
    var profile_link: String = "",
    var role: String = "User",
    var explore_cards:MutableList<ExploreCard> =mutableListOf(),
    var blogs:MutableList<Blog> =mutableListOf(),
    var images: MutableList<Image> =mutableListOf(),
    var followers: Int? = 0,
    var following: Int? = 0,
    var videos:MutableList<Video> =mutableListOf(),
    var my_profile:Boolean = false,
    var comments:MutableList<CommentPost> = mutableListOf(),
    var interests:MutableList<String> = mutableListOf(),
)
