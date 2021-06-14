package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.dtos.ExploreCardClientDto
import com.wisaly.wisaly_kotlin_spring.service.ExploreService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/explore")
@CrossOrigin("http://localhost:3000", allowCredentials = "true")
class ExploreController(private val exploreService: ExploreService){

    /** Send photo id and blog ids after storing them first and then
     * saving in explore card */
    @PostMapping("/")
    fun uploadExplore(@RequestBody body: ExploreCardClientDto){
        exploreService.saveExplore(body);
    }

}