package com.wisaly.wisaly_kotlin_spring.service.Product

import com.wisaly.wisaly_kotlin_spring.repository.Product.OrderProductRepository
import com.wisaly.wisaly_kotlin_spring.repository.Product.OrderRepository
import com.wisaly.wisaly_kotlin_spring.repository.Product.ProductRepository
import org.springframework.stereotype.Service

@Service
class OrderService (private val orderRepository: OrderRepository,
                    private val orderProductRepository: OrderProductRepository,
                    private val productRepository: ProductRepository){
    /** All transactions, purchasing, order management in this api */

    //purchase item
    /** Instantiated after transaction has completed**/
    fun purchase(user_id:Long, products:List<String>, address:String){
        val myOrder =  mutableListOf<String>()
        products.forEach {

            myOrder.add(it)
        }
    }

    /**Currently, all transactions would occur as cod.*/
}