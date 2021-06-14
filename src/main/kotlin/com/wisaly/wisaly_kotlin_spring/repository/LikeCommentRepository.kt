package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.dtos.queries.BlogLike
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LikeCommentRepository:JpaRepository<Blog,Long> {
    @Modifying
    @Query("INSERT INTO like_blog(user_id, blog_id) VALUES (?1,?2)",nativeQuery = true)
    fun likeBlog(user_id:Long,blog_id:Long)
}