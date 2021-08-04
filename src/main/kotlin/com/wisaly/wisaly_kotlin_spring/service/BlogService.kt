package com.wisaly.wisaly_kotlin_spring.service

import com.wisaly.wisaly_kotlin_spring.dtos.enums.RichTextType
import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.BlogDto
import com.wisaly.wisaly_kotlin_spring.models.RichTextContent
import com.wisaly.wisaly_kotlin_spring.repository.BlogRepository
import com.wisaly.wisaly_kotlin_spring.repository.CategoryRepository
import com.wisaly.wisaly_kotlin_spring.repository.RichTextRepository
import com.wisaly.wisaly_kotlin_spring.repository.UserRepository
import com.wisaly.wisaly_kotlin_spring.utils.JsonMapper
import com.wisaly.wisaly_kotlin_spring.utils.Slugify
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.BlogMapper
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.lang.IllegalStateException


@Service
class BlogService(
    private val blogRepository: BlogRepository,
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository,
    private val blogMapper: BlogMapper,
    private val richTextRepository: RichTextRepository
) {
    fun fetchAllBlogs(): String {
        val blog = blogRepository.getAllNonArchivedBlogs()
        return JsonMapper.convertJson(blog)
    }

    fun createNewBlog(user_id: Long, blogContent: BlogDto): String {

        val author = userRepository.findById(user_id).orElseGet(null)
//        /** getting categories and tags for the user*/
        val myContent = RichTextContent(id=0,type=RichTextType.BLOG,content=blogContent.content)
        blogContent.contentEntity = richTextRepository.save(myContent)
        val myBlog = blogMapper.toEntity(blogContent)
        myBlog.author = author
        myBlog.pagelink = Slugify.SlugifyString(blogContent.title)
        val myCategory = categoryRepository.findByCategory("Flowers")
        blogContent.categories.forEach {
            myBlog.categories.add(
                categoryRepository.findByCategory(it) ?: throw IllegalStateException("No category found")
            )
        }
        val newBlog = blogRepository.save(myBlog)
//        blogContent.tags.forEach{
//          blogRepository.addTags(newBlogDto.id,it)
//        }
//        blogContent.categories.forEach{
//            blogRepository.addCategories(newBlogDto.id,it)
//        }
//        newBlogDto.categories = blogContent.categories
//        newBlogDto.tags = blogContent.tags
//        return jsonMapper.writeValueAsString(newBlogDto)
        //return JsonMapper.convertJson(myBlog)
        return JsonMapper.convertJson(blogMapper.fromEntity(newBlog))
    }

    fun getCompleteBlog(blog_link: String, user_id: Long?): BlogDto {
//        return blogRepository.getCompleteBlog(blog_link)?.let { JsonMapper.convertJson(it) }
        val user = user_id ?: -1
        val blog = blogRepository.findByPagelink(blog_link) ?: throw IllegalStateException("No blog found");
        val authorDetails = blog.author ?: throw IllegalStateException("Author Doesnt exist");
        val owner = authorDetails.id == user
        val liked = blogRepository.likedByUser(user, blog.id)
        //print()
        val likes = blogRepository.getLikes(blog.id)
        val result = blogMapper.completeBlogDto(blog, authorDetails)
        result.likes = likes
        result.likedByUser = liked
        result.owner = owner
        return result
    }

    fun getAllBlogs(size: Int, page: Int): List<BlogDto>{
        val blogList = mutableListOf<BlogDto>()
            blogRepository.findAll(PageRequest.of(page,size)).forEach { blogList.add(blogMapper.fromEntity(it)) }
        return blogList
    }
}