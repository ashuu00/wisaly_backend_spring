package com.wisaly.wisaly_kotlin_spring.models.user


import com.fasterxml.jackson.annotation.JsonIgnore
import com.wisaly.wisaly_kotlin_spring.dtos.enums.Genders
import com.wisaly.wisaly_kotlin_spring.models.*
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
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column
    var created_at: Timestamp,

    @Column
    var updated_at: Timestamp,

    @Column(unique = true)
    var email: String = "",

    @Column(name = "first_name")
    var firstname: String = "",

    @Column(name = "last_name")
    var lastname: String = "",

    @Column(columnDefinition = "varchar(30)",unique=true)
    var username: String = email.split("@")[0],

    @Column
    var display_pic: String = "",

    @Column
    var phone_no: String = "",

    @Column(name="account_owner")
    var accountOwner: Boolean = false,

    @Enumerated(EnumType.STRING)
    var gender: Genders = Genders.UNDEFINED,

    @Column(name = "user_page_link")
    var userPageLink: String,

    @Column(columnDefinition = "varchar(200)")
    var about:String?= null,

    @Column(columnDefinition ="varchar(50)")
    var city:String?= null,

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    var interests:MutableList<Category> = mutableListOf(),

    @Column
    var profile_complete: Boolean = false,

    @Column(columnDefinition = "varchar(30)")
    var role: String = "USER",

    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    var explore_cards: MutableList<ExploreCard> = mutableListOf(),

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    open var blogs: MutableList<Blog> = mutableListOf(),

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonIgnore
    open var images: MutableList<Image> =mutableListOf(),

    @ManyToMany(mappedBy = "following",fetch = FetchType.LAZY)
    @JsonIgnore
    var followers: MutableList<User> = mutableListOf(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_relationship")
    @JsonIgnore
    var following: MutableList<User> = mutableListOf(),

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    val comments:MutableList<Comment> = mutableListOf(),

//    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
//    var draftimages:MutableList<Draftimages> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    var videos: MutableList<Video> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    var commentPosts: MutableList<CommentPost> = mutableListOf(),

    /**Maps Id makes it one-directional, hence no eager fetching*/
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade =[CascadeType.PERSIST])
    var feedbacks: MutableList<Feedback> = mutableListOf()

)