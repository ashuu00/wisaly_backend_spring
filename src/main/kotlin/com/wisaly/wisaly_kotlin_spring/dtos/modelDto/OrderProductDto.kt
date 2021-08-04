package com.wisaly.wisaly_kotlin_spring.dtos.modelDto

import javax.persistence.Column

data class OrderProductDto(
    var id: Long=-1,
    var name: String?,
    var link: String?,
    var about: String?,
    var price: Float =0.0f,
    var discount:Float = 0.0f,
    var status: String = "",
    var image: String ="",
)
