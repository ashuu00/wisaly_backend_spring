package com.wisaly.wisaly_kotlin_spring.models.exploreCard

import com.fasterxml.jackson.annotation.JsonIgnore
import com.wisaly.wisaly_kotlin_spring.models.*
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
class ExploreCard(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long=-1,

    @Column
    var created_at: Timestamp = Timestamp.from(Instant.now()),

    @Column
    var updated_at: Timestamp = Timestamp.from(Instant.now()),

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
     var user: User? = null,

    @Column()
    var archived: Boolean= false,

    @Column()
    var title:String = "",

    @Column(name = "card_link")
    var link: String ="",

    @Column(columnDefinition = "varchar(500)")
    var description: String = "",

    @JsonIgnore
    @ManyToMany(mappedBy = "cards",fetch = FetchType.LAZY)
     var images:MutableList<Image> = mutableListOf(),

    @JsonIgnore
    @ManyToMany(mappedBy = "cards",fetch = FetchType.LAZY)
    var blogs:MutableList<Blog> = mutableListOf(),

    @ManyToMany(mappedBy = "cards",fetch = FetchType.LAZY)
    var categories:MutableList<Category> = mutableListOf(),

    @JsonIgnore
    @ManyToMany(mappedBy = "cards",fetch = FetchType.LAZY)
    var videos:MutableList<Video> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    var comments: MutableList<Comment> = mutableListOf(),

    @OneToMany(mappedBy ="card", fetch = FetchType.LAZY)
    var medias: MutableList<Media> = mutableListOf()

//    @ManyToMany(mappedBy = "cards", fetch = FetchType.EAGER)
//    var keywords:MutableList<Keyword>

)