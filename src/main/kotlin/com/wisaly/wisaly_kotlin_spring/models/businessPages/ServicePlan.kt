package com.wisaly.wisaly_kotlin_spring.models.businessPages

import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
class ServicePlan (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,
    val createdAt: Timestamp = Timestamp.from(Instant.now()),
    var updatedAt: Timestamp = Timestamp.from(Instant.now()),
    val title: String,
    val price: Float = 0.0f,
    val discount: Float = 0.0f,
    val description: String,
    @ManyToOne(fetch = FetchType.LAZY)
    val service:WisalyService
)
{}