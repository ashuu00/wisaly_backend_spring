package com.wisaly.wisaly_kotlin_spring.models.blog

import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Tag
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name="blog",indexes = [Index(name="authorIndex",columnList = "author_id")])
data class Blog(
    /* these are the auto-generated values*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,

    @Column
    var created_at: Timestamp = Timestamp.from(Instant.now()),

    @Column
    var updated_at: Timestamp = Timestamp.from(Instant.now()),

    /* end of the auto-generated values*/

    @Column
     var blog_title: String="",

    @Column
     var blog_description: String="",

    @Column
    var title_pic: String="",

    @Column()
    var archived: Boolean = false,

    @Column()
    var draft: Boolean = false,

    @Column(columnDefinition = "text")
    var blog_content: String = "",

    @ManyToMany
    var cards :MutableList<ExploreCard> = mutableListOf(),

    @ManyToOne()
    var author: User ,


)

