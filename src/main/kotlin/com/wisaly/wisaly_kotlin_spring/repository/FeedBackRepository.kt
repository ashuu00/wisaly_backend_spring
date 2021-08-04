package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.models.Feedback
import org.springframework.data.jpa.repository.JpaRepository

interface FeedBackRepository:JpaRepository<Feedback, Long> {

}