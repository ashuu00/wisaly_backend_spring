package com.wisaly.wisaly_kotlin_spring.models

import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
 class Comment(
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,

   @Column(name="created_at")
    var createdAt: Timestamp  = Timestamp.from(Instant.now()),

   @ManyToOne(fetch = FetchType.LAZY)
    var author: User? = null,

   @ManyToOne(fetch = FetchType.LAZY)
    val blog: Blog? = null,

   @ManyToOne(fetch = FetchType.LAZY)
   var card: ExploreCard? = null,

   val content: String,

   @ManyToOne(fetch = FetchType.LAZY)
    val parent: Comment? = null

    )
