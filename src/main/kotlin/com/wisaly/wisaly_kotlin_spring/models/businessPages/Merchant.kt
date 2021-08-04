package com.wisaly.wisaly_kotlin_spring.models.businessPages

import com.wisaly.wisaly_kotlin_spring.models.Rating
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
class Merchant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,
    val createdAt: Timestamp = Timestamp.from(Instant.now()),
    var updatedAt: Timestamp = Timestamp.from(Instant.now()),
    @Column(columnDefinition="varchar(80)")
    var name:String,
    var username:String,
    var about:String,
    var websiteLink:String?=null,

    @ManyToOne(fetch = FetchType.LAZY)
    var owner: User,

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
    var ratings:MutableList<Rating>  = mutableListOf(),

    ){}