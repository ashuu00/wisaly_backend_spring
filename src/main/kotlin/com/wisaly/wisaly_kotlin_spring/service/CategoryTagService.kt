package com.wisaly.wisaly_kotlin_spring.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Tag
import com.wisaly.wisaly_kotlin_spring.repository.CategoryRepository
import com.wisaly.wisaly_kotlin_spring.repository.TagRepository
import org.springframework.stereotype.Service

@Service
class CategoryTagService(
    private val categoryRepository: CategoryRepository,
    private val tagRepository: TagRepository
) {
    fun createNew(name:String, type:String):String{
        val jsonMapper = jacksonObjectMapper()
        when(type){
            "category"->return jsonMapper.writeValueAsString(categoryRepository.save(Category(name)))
            "tag"-> return jsonMapper.writeValueAsString(tagRepository.save(Tag(name)))
            else -> throw NoSuchFieldException("Type not declared correctly")
        }
    }

    fun getCategory():List<Category>{
        return categoryRepository.findAll()
    }
}