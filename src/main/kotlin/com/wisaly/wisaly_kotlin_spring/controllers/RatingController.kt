package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.RatingDto
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/rating")
@CrossOrigin("http://localhost:3000")
class RatingController {

    @PostMapping("/add/{id}")
    fun addRating(@RequestAttribute("user_id") user_id: Long?,
                  @PathVariable("id") id: Long,
                  @RequestParam("type") type: String,
                  @RequestBody data: RatingDto) {
        if (user_id == null) throw IllegalArgumentException("user not authorized to add reviews")

    }
}