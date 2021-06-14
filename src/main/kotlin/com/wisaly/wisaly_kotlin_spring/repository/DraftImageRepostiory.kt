package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.models.Image
import org.springframework.data.jpa.repository.JpaRepository

interface DraftImageRepostiory:JpaRepository<Image, Long> {
}