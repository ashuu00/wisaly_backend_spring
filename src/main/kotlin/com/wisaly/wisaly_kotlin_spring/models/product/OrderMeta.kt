package com.wisaly.wisaly_kotlin_spring.models.product

import com.wisaly.wisaly_kotlin_spring.models.user.User
import org.hibernate.annotations.Type
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
class OrderMeta (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var createdAt: Timestamp = Timestamp.from(Instant.now()),

    @ManyToOne
    var user: User,

//    @OneToMany(mappedBy="orders")
//    var products: MutableList<OrderProduct> = mutableListOf(),

    var status:String="",
    var address:String="",


    )