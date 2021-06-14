package com.wisaly.wisaly_kotlin_spring.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import lombok.AllArgsConstructor
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*


@Entity
data class Category (
    /* these are the auto-generated values*/
    @Id
    @Column(unique = true, columnDefinition = "VARCHAR(80)")
    var category_name: String,

    @Column
    var created_at: Timestamp = Timestamp.from(Instant.now()),


    /* end of the auto-generated values*/

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    var cards:MutableList<ExploreCard> = mutableListOf(),


)