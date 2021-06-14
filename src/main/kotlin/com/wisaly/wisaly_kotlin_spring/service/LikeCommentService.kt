package com.wisaly.wisaly_kotlin_spring.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.wisaly.wisaly_kotlin_spring.repository.LikeCommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeCommentService(private val likeCommentRepository: LikeCommentRepository){
    @Transactional
    fun likeBlog(user_id:Long, blog_id:Long):String{
        val jsonMapper = jacksonObjectMapper()
        return jsonMapper.writeValueAsString(likeCommentRepository.likeBlog(user_id, blog_id))
    }
}