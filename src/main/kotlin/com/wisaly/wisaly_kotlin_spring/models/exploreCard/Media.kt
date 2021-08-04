package com.wisaly.wisaly_kotlin_spring.models.exploreCard

import javax.persistence.*

@Entity
@Table
class Media(
    @Id
    @Column(unique = true)
    val link: String = "null",

    @ManyToOne(fetch = FetchType.LAZY)
    var card: ExploreCard?= null,
//
    @Column(columnDefinition="smallint")
    var mediaOrder: Int = 0,

    @Column
  var isVideo: Boolean = false

)