package com.wisaly.wisaly_kotlin_spring.repository.WisalyService

import com.wisaly.wisaly_kotlin_spring.models.businessPages.WisalyService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface WisalyServiceRepository: JpaRepository<WisalyService, Long> {
}