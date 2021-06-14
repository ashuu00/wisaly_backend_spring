package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.models.Video
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository:JpaRepository<Video, Long> {
}