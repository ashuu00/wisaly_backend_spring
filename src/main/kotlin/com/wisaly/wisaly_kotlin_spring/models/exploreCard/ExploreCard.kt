package com.wisaly.wisaly_kotlin_spring.models.exploreCard

import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Image
import com.wisaly.wisaly_kotlin_spring.models.Tag
import com.wisaly.wisaly_kotlin_spring.models.Video
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
data class ExploreCard(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column
    var created_at: Timestamp = Timestamp.from(Instant.now()),

    @Column
    var updated_at: Timestamp = Timestamp.from(Instant.now()),


    @ManyToOne()
     var user: User,

    @Column()
    var archived: Boolean,

    @Column()
    var title:String = "",

    @Column(columnDefinition = "varchar(500)")
    var description: String = "",

    @ManyToMany(mappedBy = "cards",fetch = FetchType.LAZY)
     var images:MutableList<Image>,


    @ManyToMany(mappedBy = "cards",fetch = FetchType.LAZY)
    var blogs:MutableList<Blog>,

    @ManyToMany()
    var categories:MutableList<Category>,

    @ManyToMany(mappedBy = "cards")
    var videos:MutableList<Video>,

    @ManyToMany()
    var tags:MutableList<Tag>

)