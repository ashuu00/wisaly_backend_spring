package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

import com.wisaly.wisaly_kotlin_spring.models.Rating
import com.wisaly.wisaly_kotlin_spring.models.businessPages.ServicePlan
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant

data class WisalyServiceDto (
    var id:Long,
    val createdAt: Timestamp = Timestamp.from(Instant.now()),
    var updatedAt: Timestamp = Timestamp.from(Instant.now()),
    var title:String,
    var description:String,
    var businessName: User?=null,
    var ratings:MutableList<Rating> = mutableListOf(),
    var plans:List<ServicePlan> = mutableListOf()
    )