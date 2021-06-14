package com.wisaly.wisaly_kotlin_spring.service

import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.repository.ExploreRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service

@Service
class PublicAccessService(private val exploreRepository: ExploreRepository) {

    //getting all cards for the dashboard page for new users, without any filter
    fun getAllCards(pageNo: Int):Slice<ExploreCard>{
        val limit=24
        val items = PageRequest.of(pageNo,limit)
        val allCards:Slice<ExploreCard> = this.exploreRepository.findAll(items)
        return allCards;
    }
}