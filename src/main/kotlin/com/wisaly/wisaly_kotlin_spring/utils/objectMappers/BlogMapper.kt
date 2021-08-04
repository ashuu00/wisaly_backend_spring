package com.wisaly.wisaly_kotlin_spring.utils.objectMappers

import com.wisaly.wisaly_kotlin_spring.dtos.UserBlogsDto
import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.BlogDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.Blog.UserBlogs
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.user.User
import org.springframework.stereotype.Service

@Service
class BlogMapper : Mapper<BlogDto, Blog> {
    override fun toEntity(domain: BlogDto): Blog = Blog(
        title = domain.title,
        description = domain.description,
        title_pic = domain.title_pic,
        archived = domain.archived,
        content = domain.contentEntity!!
        //author = domain.author!!,
    )

    override fun fromEntity(entity: Blog): BlogDto = BlogDto(
        title = entity.title,
        description = entity.description,
        title_pic = entity.title_pic,
        id = entity.id,
        created = entity.created_at,
        blog_link =  entity.pagelink,
        content =""
    )

    fun fromUserBlogs(entity: UserBlogs): UserBlogsDto = UserBlogsDto(
        title = entity.title,
        description = entity.description,
        id = entity.id,
        likes = entity.likes,
        created = entity.created_at,
        updated = entity.updated_at,
        archived = entity.archived,
        display_pic = entity.title_pic,
        blog_link = entity.page_link,

    )

    fun completeBlogDto(blog:Blog, author:User):BlogDto = BlogDto(
        id = blog.id,
        created = blog.created_at,
        updated = blog.updated_at,
        title = blog.title,
        description = blog.description,
        title_pic = blog.title_pic,
        archived = blog.archived,
        draft = blog.draft,
        content = blog.content?.content?:"",
        blog_link = blog.pagelink,
        authorName = "${author.firstname} ${author.lastname}",
        authorId = author.id,
        authorPage = author.userPageLink,
        categories = blog.categories.map { it.category },
        authorPic = author.display_pic,
        authorDescription = author.about?:"",
    )

}
