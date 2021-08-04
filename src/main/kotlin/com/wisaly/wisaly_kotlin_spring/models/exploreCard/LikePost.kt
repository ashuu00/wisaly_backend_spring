package com.wisaly.wisaly_kotlin_spring.models.exploreCard

import com.wisaly.wisaly_kotlin_spring.dtos.enums.LikeType
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.io.Serializable
import javax.persistence.*

//Add indexes

data class likeCardId(
   val post:ExploreCard,
   val author: User,
): Serializable

@Table(name = "like_card",)
@Entity
@IdClass(likeCardId::class)
 class LikePost(


    @Enumerated(EnumType.STRING)
    var likeType: LikeType,

    @ManyToOne()
    @JoinColumn(name = "card_id")
    @Id
    var post: ExploreCard,

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Id
    var author: User

)
