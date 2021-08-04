package com.wisaly.wisaly_kotlin_spring.models

import com.wisaly.wisaly_kotlin_spring.dtos.enums.RichTextType
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import org.springframework.data.annotation.Id
import javax.persistence.*

@Entity
class RichTextContent (
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,

    @Enumerated(EnumType.STRING)
    var type: RichTextType,

    @Column(columnDefinition="text")
    var content:String,

    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
    var blog: MutableList<Blog> = mutableListOf(),


//    @OneToOne(mappedBy = "content")
//    var card: ExploreCard?=null


        ) {
}