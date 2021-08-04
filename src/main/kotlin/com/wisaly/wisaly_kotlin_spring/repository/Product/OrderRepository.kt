package com.wisaly.wisaly_kotlin_spring.repository.Product

import com.wisaly.wisaly_kotlin_spring.models.product.OrderMeta
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderMeta, Long> {
}