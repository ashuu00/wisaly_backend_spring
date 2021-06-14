package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

import com.wisaly.wisaly_kotlin_spring.dtos.enums.Genders
import com.wisaly.wisaly_kotlin_spring.models.*
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.CommentPost
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.Follower
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
    val first_name:String = "",
    val last_name:String = "",
    var display_pic:String = "",
    var phone_no:String = "",
    var gender: Genders = Genders.UNDEFINED,
    var profile_complete: Boolean = false,
    var roles: String = "User",
    val explore_cards:MutableList<ExploreCard> =mutableListOf(),
    val blogs:MutableList<Blog> =mutableListOf(),
    val images: MutableList<Image> =mutableListOf(),
    val follower: MutableList<Follower> =mutableListOf(),
    var following: MutableList<Follower> =mutableListOf(),
    val videos:MutableList<Video> =mutableListOf(),
    var my_profile:Boolean = false,
    var comments:MutableList<CommentPost> = mutableListOf()
)
