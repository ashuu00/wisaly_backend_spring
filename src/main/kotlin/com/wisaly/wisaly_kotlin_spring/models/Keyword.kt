package com.wisaly.wisaly_kotlin_spring.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

//@Table(name = "keyword")
@Entity
class Keyword(
    @Id
    @Column(name = "keyword",unique = true, columnDefinition = "VARCHAR(80)")
    var tag_name:String,

    @Column
    var created_at: Timestamp = Timestamp.from(Instant.now()),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name="keywords_cards",joinColumns = [JoinColumn(name="keyword")],
        inverseJoinColumns = [JoinColumn(name="card_id")])
    var cards: MutableList<ExploreCard> = mutableListOf()

)
