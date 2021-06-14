package com.wisaly.wisaly_kotlin_spring.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Photo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,


    )
