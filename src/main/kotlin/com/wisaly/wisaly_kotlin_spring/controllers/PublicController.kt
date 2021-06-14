package com.wisaly.wisaly_kotlin_spring.controllers


import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.service.PublicAccessService
import org.springframework.data.domain.Slice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/public")
class PublicController (private val publicService: PublicAccessService){
    @GetMapping("/all-cards")
    fun getAllCards( @RequestParam("pageNo", defaultValue = "0") pageNo: String):Slice<ExploreCard>{
        return this.publicService.getAllCards(pageNo.toInt())
    }
}