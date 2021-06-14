package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExploreRepository: JpaRepository<ExploreCard, Long> {
}