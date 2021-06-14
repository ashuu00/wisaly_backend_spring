package com.wisaly.wisaly_kotlin_spring.models.user


import com.fasterxml.jackson.annotation.JsonIgnore
import com.wisaly.wisaly_kotlin_spring.dtos.enums.Genders
import com.wisaly.wisaly_kotlin_spring.models.Image
import com.wisaly.wisaly_kotlin_spring.models.Video
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.CommentPost
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(
    name = "wisaly_user",
    indexes = [Index(name = "name_index", columnList = "first_name"),
            Index(name="created_user", columnList = "created_at") ])
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column
    var created_at: Timestamp,

    @Column
    var updated_at: Timestamp,

    @Column(unique = true)
    var email: String = "",

    @Column
    var first_name: String = "",

    @Column
    var last_name: String = "",

    @Column
    var display_pic: String = "",

    @Column
    var phone_no: String = "",

    @Column
    var account_owner: Boolean = false,

    @Enumerated(EnumType.STRING)
    var gender: Genders = Genders.UNDEFINED,

    @Column
    var profile_complete: Boolean = false,

    @Column(columnDefinition = "varchar(30)")
    var roles: String = "USER",

    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    var explore_cards: MutableList<ExploreCard>,

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    open var blogs: MutableList<Blog>,

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonIgnore
    open var images: MutableList<Image>,

    @OneToMany(mappedBy = "lead_user",fetch = FetchType.LAZY)
    @JsonIgnore
    var followers: MutableList<Follower>,

    @OneToMany(mappedBy = "following_user",fetch = FetchType.LAZY)
    @JsonIgnore
    var following: MutableList<Follower>,

//    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
//    var draftimages:MutableList<Draftimages> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    var videos: MutableList<Video>,

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    var comments: MutableList<CommentPost>

)