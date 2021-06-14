package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BlogRepository:JpaRepository<Blog, Long> {
    /** We cant create custom functions inside this as its just interface*/
//    fun savedBlog(blog:blogUploadDto, author: User):Blog{
//        val newBlog=Blog()
//        newBlog.author = author
//        newBlog.blog_content = blog.blog_content
//        newBlog.blog_description = blog.blog_description
//        newBlog.blog_title = blog.blog_title
//        newBlog.title_pic = blog.title_pic
//        return save(newBlog)
//    }

//    @Query('SELECT ',nativeQuery = true)
//    fun getAllBlogByUserId()
//
//    @Query('',nativeQuery = true)
//    fun getBlogByBlogId(){
//
//    }
//UPDATE blog B SET B.archived = TRUE WHERE B.id=?1

    @Modifying
    @Query("UPDATE blog  SET archived = TRUE WHERE id=?1 AND author_id=?2 returning *",nativeQuery = true)
    fun updateBlogArchived(blogId:Long, author_id:Long)

    @Modifying
    @Query("INSERT INTO blog_categories(blog_id, category_index) VALUES (?1,?2)", nativeQuery = true)
    fun addCategories(blogId:Long, category:String)

    @Modifying
    @Query("INSERT INTO blog_tags(blogs_id, tag_name) VALUES (?1,?2)", nativeQuery = true)
    fun addTags(blogId:Long, tag:String)
}