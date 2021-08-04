package com.wisaly.wisaly_kotlin_spring.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
class Feedback (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,

    @Column(name="created_at")
    var createdAt: Timestamp  = Timestamp.from(Instant.now()),
    var message: String,
    var rating:Float,

    @ManyToOne(fetch = FetchType.LAZY)
    var user:User? = null,

    )