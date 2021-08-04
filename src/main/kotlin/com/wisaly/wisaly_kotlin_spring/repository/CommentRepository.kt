package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.models.Comment
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.CommentPost
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository:JpaRepository<Comment, Long> {

}