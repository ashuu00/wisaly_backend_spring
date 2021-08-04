package com.wisaly.wisaly_kotlin_spring.dtos.queries.product

interface CreateProduct {
    val title: String
    val description: String
    val images: String
    val categories: String
    val tags: String
    val cost: Float
    val discount: Float
    val quantity: Int
    val buy_limit: Int?
    val seller_id: Long
    val completeDescription: Long //another table for all html content
}