package com.wisaly.wisaly_kotlin_spring.utils.objectMappers

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.BlogDto
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import org.springframework.stereotype.Service

@Service
class BlogMapper:Mapper<BlogDto,Blog>{
    override fun toEntity(domain: BlogDto): Blog = Blog(
        blog_title = domain.title,
        blog_description = domain.description,
        title_pic = domain.title_pic,
        blog_content = domain.content,
        author = domain.author!!,
    )

    override fun fromEntity(entity: Blog): BlogDto = BlogDto(
        title = entity.blog_title,
        description = entity.blog_description,
        title_pic = entity.title_pic,
        content = entity.blog_content,
        author = entity.author,
        id =entity.id,
        cards = entity.cards,
        created = entity.created_at,
        updated = entity.updated_at,
    )

}
