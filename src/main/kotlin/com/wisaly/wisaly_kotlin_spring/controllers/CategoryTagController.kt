package com.wisaly.wisaly_kotlin_spring.controllers

import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.service.CategoryTagService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class CategoryTagController(private val categoryTagService: CategoryTagService) {
    enum class CategoryOrTag(val typeName:String) {
        CATEGORY("category"), TAG("tag")
    }

    data class NameClass(
        val name: String,
        val type: String
    )


    @PostMapping("/v1/category/create")
    fun addCategoryOrTag(@RequestBody valueClass: NameClass):ResponseEntity<String>{
        println("Category from enum is ${CategoryOrTag.CATEGORY.typeName}")
        when (valueClass.type) {
            CategoryOrTag.CATEGORY.typeName -> return ResponseEntity(
                categoryTagService.createNew(valueClass.name, "category"),
                HttpStatus.CREATED
            )
            CategoryOrTag.TAG.typeName -> return ResponseEntity(
                categoryTagService.createNew(valueClass.name, "tag"),
                HttpStatus.CREATED
            )
            else -> throw Exception("Category Type Not Defined")
        }

    }

    @GetMapping("/v1/category/getall")
    fun getAll():List<Category>{
        return categoryTagService.getCategory()
    }
}