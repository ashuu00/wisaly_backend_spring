package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.ExploreCardDto
import com.wisaly.wisaly_kotlin_spring.service.ExploreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/explore")
@CrossOrigin("http://localhost:3000", allowCredentials = "true")
class ExploreController(private val exploreService: ExploreService) {

    /** Send photo id and blog ids after storing them first and then
     * saving in explore card */
    @PostMapping("/new")
    fun uploadExplore(@RequestBody body: ExploreCardDto, @RequestAttribute user_id: Long): ExploreCardDto {
        return exploreService.saveExplore(body, user_id);
    }

    @GetMapping("/{card_link}")
    fun getSingleExplore(
        @PathVariable("card_link") card_link: String,
        @RequestAttribute user_id: Long?
    ): ResponseEntity<String> {
        println("Card path in controller $card_link")
        return ResponseEntity.ok(exploreService.getCompleteCard(card_link, user_id))
    }

    @GetMapping("/all")
    fun getAllExplore(@RequestParam("page") page: Int, @RequestParam("limit") limit: Int): ResponseEntity<String> {
        return ResponseEntity.ok(exploreService.getAllCards(page, limit))
    }

    @GetMapping("/like")
    fun likeCard(
        @RequestParam("card") card_id: Long,
        @RequestParam("isLiked") isLiked: Boolean,
        @RequestAttribute("user_id") user_id: Long
    ): ResponseEntity<String> {
        return ResponseEntity.ok(exploreService.likeCard(user_id, card_id, isLiked))
    }


}