package com.wisaly.wisaly_kotlin_spring.dtos.queries.explore_card

import java.sql.Timestamp

interface BasicExplore {
    var id: Long
    var created_at: Timestamp
    var updated_at: Timestamp
    var archived: Boolean
    var title:String
    var description: String
    val card_page_link: String
    val full_name:String
    val profile_pic:String
    val user_link: String
}