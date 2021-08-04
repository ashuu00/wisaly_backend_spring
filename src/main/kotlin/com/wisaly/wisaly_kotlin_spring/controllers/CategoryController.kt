package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.dtos.client.NameClass
import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.service.CategoryTagService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin("http://localhost:3000", allowCredentials = "true")
class CategoryController(private val categoryTagService: CategoryTagService) {




    @PostMapping("/create")
    fun addCategory(@RequestBody valueClass: NameClass):ResponseEntity<String>{
        return ResponseEntity.ok(categoryTagService.createNew(valueClass.name,"category"))
    }


    @GetMapping("/getall")
    fun getAll():List<Category>{
        println("get all cateogires without users")
        return categoryTagService.getCategory()
    }
}