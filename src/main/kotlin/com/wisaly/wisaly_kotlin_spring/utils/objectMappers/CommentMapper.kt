package com.wisaly.wisaly_kotlin_spring.utils.objectMappers

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.CommentDto
import com.wisaly.wisaly_kotlin_spring.models.Comment
import com.wisaly.wisaly_kotlin_spring.utils.TimeDifferenceToString
import org.springframework.stereotype.Service

@Service
class CommentMapper:Mapper<CommentDto, Comment> {
    override fun toEntity(domain: CommentDto): Comment = Comment(
        domain.id,
        content = domain.content,
    )
    override fun fromEntity(entity: Comment): CommentDto = CommentDto(
        entity.id,
        entity.content,
        "${entity.author?.firstname} ${entity.author?.lastname}",
        entity.author?.id,
        entity.author?.username!!,
        entity.author?.display_pic?:"",
        TimeDifferenceToString.convertToDate(entity.createdAt)
    )

}