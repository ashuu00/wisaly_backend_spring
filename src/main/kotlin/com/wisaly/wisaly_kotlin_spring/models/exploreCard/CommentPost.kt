package com.wisaly.wisaly_kotlin_spring.models.exploreCard

import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

//need to test the
@Entity
 class CommentPost(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column
    var created_at: Timestamp = Timestamp.from(Instant.now()),

    @Column
    var updated_at: Timestamp = Timestamp.from(Instant.now()),

    @Column
    var comment_data: String = "",

    @ManyToOne()
    var parent: CommentPost? = null,

    @OneToMany
    var replies:MutableList<CommentPost> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "card_id")
    var card: ExploreCard,

    @ManyToOne //one user can add multiple comments
    @JoinColumn(name="author_id")
    var author:User


    )
