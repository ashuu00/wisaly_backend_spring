package com.wisaly.wisaly_kotlin_spring.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
data class Tag(
    @Id
    @Column(name = "TAG_NAME",unique = true, columnDefinition = "VARCHAR(80)")
    var tag_name:String,

    @Column
    var created_at: Timestamp = Timestamp.from(Instant.now()),



    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    var cards: MutableList<ExploreCard> = mutableListOf()

)
