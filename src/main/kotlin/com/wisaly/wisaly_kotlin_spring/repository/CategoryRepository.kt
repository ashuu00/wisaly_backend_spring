package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.dtos.queries.CategoryFull
import com.wisaly.wisaly_kotlin_spring.models.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository:JpaRepository<Category, Long> {

    @Query("SELECT * FROM category ",nativeQuery = true)
    fun getAllCategories()

    fun findByCategory(category:String):Category?

    @Query("SELECT * FROM category C WHERE C.category_name=?1",nativeQuery = true)
    fun getCategory(category_name:String):CategoryFull
}