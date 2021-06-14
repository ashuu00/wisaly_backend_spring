package com.wisaly.wisaly_kotlin_spring.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.wisaly.wisaly_kotlin_spring.dtos.blogUploadDto
import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.BlogDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.CategoryFull
import com.wisaly.wisaly_kotlin_spring.dtos.queries.TagFull
import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Tag
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.repository.BlogRepository
import com.wisaly.wisaly_kotlin_spring.repository.CategoryRepository
import com.wisaly.wisaly_kotlin_spring.repository.TagRepository
import com.wisaly.wisaly_kotlin_spring.repository.UserRepository
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.BlogMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class BlogService(private val blogRepository: BlogRepository,
                  private val userRepository: UserRepository,
                  private val categoryRepository: CategoryRepository,
                  private val tagRepository: TagRepository,
                  private val blogMapper: BlogMapper){
    fun fetchAllBlogs():List<BlogDto>{
        val allBlogs=blogRepository.findAll()
        val blogsDto = mutableListOf<BlogDto>()
        for(blog in allBlogs){
            blogsDto.add(blogMapper.fromEntity(blog))
        }
        return blogsDto
    }

    @Transactional
    fun createNewBlog(user_id:Long, blogContent:BlogDto):String{
        val jsonMapper = jacksonObjectMapper()
        val author = userRepository.findById(user_id).orElseGet(null)
        /** getting categories and tags for the user*/
        blogContent.author = author
        val newBlog = blogRepository.save(blogMapper.toEntity(blogContent))
        val newBlogDto = blogMapper.fromEntity(newBlog)
        blogContent.tags.forEach{
          blogRepository.addTags(newBlogDto.id,it)
        }
        blogContent.categories.forEach{
            blogRepository.addCategories(newBlogDto.id,it)
        }
        newBlogDto.categories = blogContent.categories
        newBlogDto.tags = blogContent.tags
        return jsonMapper.writeValueAsString(newBlogDto)
    }
}