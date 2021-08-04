package com.wisaly.wisaly_kotlin_spring.models.product

import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
class OrderProduct (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(columnDefinition = "varchar(80)")
    var name: String,
    var link: String,
    var about: String,
    var price: Float =0.0f,
    var discount:Float = 0.0f,
    var status: String = "",
    var image: String ="",
)