package com.wisaly.wisaly_kotlin_spring.repository.Product

import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.product.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository:JpaRepository<Product,Long>{
    @Query("SELECT * FROM product  ORDER BY created_at DESC LIMIT ?2 OFFSET ?1",nativeQuery = true)
    fun getAllProducts(page_no:Int, limit: Int)

//    @Query("SELECT * FROM product JOIN    ORDER BY created_at DESC LIMIT ?2 OFFSET ?1",nativeQuery = true)
//    fun getProductsForCategory(category: String,page_no:Int, limit: Int)
fun findByLink(username:String):Product?

fun findAllByCategoryAndInStock(category: Category,inStock:Boolean):List<Product>




   // @Query()
}