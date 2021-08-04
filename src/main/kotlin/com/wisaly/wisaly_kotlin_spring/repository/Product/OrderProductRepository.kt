package com.wisaly.wisaly_kotlin_spring.repository.Product

import com.wisaly.wisaly_kotlin_spring.models.product.OrderProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderProductRepository: JpaRepository<OrderProduct, Long> {

}