package com.wisaly.wisaly_kotlin_spring.models

import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
data class Video(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,


    @Column //must generate current time-stamp
    var created_at: Timestamp = Timestamp.from(Instant.now()),


    @Column
    var link: String ="",

    @Column
    var archived: Boolean = false,

    @ManyToMany
    var cards: MutableList<ExploreCard> = mutableListOf(),

    @ManyToOne
    var author: User
)
