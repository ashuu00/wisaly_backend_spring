package com.wisaly.wisaly_kotlin_spring.service

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.CommentDto
import com.wisaly.wisaly_kotlin_spring.models.Comment
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.repository.CommentRepository
import com.wisaly.wisaly_kotlin_spring.repository.ExploreRepository
import com.wisaly.wisaly_kotlin_spring.repository.UserRepository
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.CommentMapper
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val userRepository: UserRepository,
    private val commentMapper: CommentMapper,
    private val commentRepository: CommentRepository,
    private val exploreRepository:ExploreRepository
) {

    fun createCommentCard(user_id: Long, comment: CommentDto):String{
        val author = userRepository.findById(user_id)
        val card = exploreRepository.findById(comment.exploreCard).orElseGet { null } ?: throw IllegalStateException("No user found")
        val myComment = commentMapper.toEntity(comment)
        myComment.author = author.orElseGet { null } ?: throw IllegalStateException("No user found")
        myComment.card = card
        commentRepository.save(myComment)
        return "Saved Comment Successfully"
    }

    fun getAllCommentsCard(comments: List<Comment>):List<CommentDto>{
        val commentsDto:MutableList<CommentDto> = mutableListOf()
            comments.forEach{
             commentsDto.add(commentMapper.fromEntity(it))}
        return commentsDto
    }

   // fun deleteCommentCard()
}