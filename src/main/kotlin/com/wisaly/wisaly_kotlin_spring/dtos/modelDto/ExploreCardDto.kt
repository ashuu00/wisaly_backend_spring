package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Image
import com.wisaly.wisaly_kotlin_spring.models.Tag
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp

data class ExploreCardDto(
    var id: Long,
    var created_at: Timestamp,
    var updated_at: Timestamp,
    var title_pic: String = "",
    var categories: List<Category>,//each card should have a category,
    var user: User,
    var images:MutableList<Image>,
    var blogs:MutableList<Blog>,
    var archived: Boolean = false,
    var title:String = "",
    var description: String = "",
    var tags: MutableList<Tag>
)
