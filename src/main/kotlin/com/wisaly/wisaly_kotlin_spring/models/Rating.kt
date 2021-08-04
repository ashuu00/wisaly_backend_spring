package com.wisaly.wisaly_kotlin_spring.models

import com.wisaly.wisaly_kotlin_spring.models.businessPages.Merchant
import com.wisaly.wisaly_kotlin_spring.models.businessPages.WisalyService
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
class Rating (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long = 0,


        @Column //must generate current time-stamp
        var created_at: Timestamp = Timestamp.from(Instant.now()),

        var likes: Int = 0,

        var rating:Int = 0,

        var content: String,

        @ManyToOne(fetch = FetchType.LAZY)
        var card: ExploreCard? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        var author: User,

        @ManyToOne(fetch = FetchType.LAZY)
        var service: WisalyService,

        @ManyToOne(fetch = FetchType.LAZY)
        var merchant: Merchant,


        ){}