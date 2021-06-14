package com.wisaly.wisaly_kotlin_spring.models.exploreCard

import com.wisaly.wisaly_kotlin_spring.dtos.enums.LikeType
import com.wisaly.wisaly_kotlin_spring.models.user.User
import javax.persistence.*

//Add indexes
@Table
@Entity
data class LikePost(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,

    @Enumerated(EnumType.STRING)
    var likeType: LikeType,

    @ManyToOne
    var post: ExploreCard,

    @ManyToOne
    var author: User

)
