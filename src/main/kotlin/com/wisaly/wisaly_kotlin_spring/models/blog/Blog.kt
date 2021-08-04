package com.wisaly.wisaly_kotlin_spring.models.blog

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Comment
import com.wisaly.wisaly_kotlin_spring.models.RichTextContent
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name="blog",indexes = [Index(name="authorIndex",columnList = "author_id")])
 class Blog(
    /* these are the auto-generated values*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = -1,

    @Column
    var created_at: Timestamp = Timestamp.from(Instant.now()),

    @Column
    var updated_at: Timestamp = Timestamp.from(Instant.now()),

    /* end of the auto-generated values*/

    @Column
     var title: String="",

    @Column
     var description: String="",

    @Column
    var title_pic: String="",

    @Column()
    var archived: Boolean = false,

    @Column()
    var draft: Boolean = false,

    @Column(name = "page_link")
    var pagelink: String="",

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    var content: RichTextContent?=null ,


    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("images")
    var cards :MutableList<ExploreCard> = mutableListOf(),

    @ManyToMany(mappedBy = "blogs", fetch = FetchType.LAZY)
    var categories: MutableList<Category> = mutableListOf(),

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    var author: User? = null,


    @OneToMany(mappedBy ="blog",fetch = FetchType.LAZY)
    var comments: MutableList<Comment> = mutableListOf()

)

