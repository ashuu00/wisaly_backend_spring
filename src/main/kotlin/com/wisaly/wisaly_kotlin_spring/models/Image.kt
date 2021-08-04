package com.wisaly.wisaly_kotlin_spring.models

import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

//use join to get images for user
@Entity
 class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,


    @Column //must generate current time-stamp
    var created_at: Timestamp = Timestamp.from(Instant.now()),


    @Column
    var link: String ="",

    @Column
    var archived: Boolean = false,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var cards: MutableList<ExploreCard>,

    @ManyToOne(fetch = FetchType.LAZY)
    var author: User,



)