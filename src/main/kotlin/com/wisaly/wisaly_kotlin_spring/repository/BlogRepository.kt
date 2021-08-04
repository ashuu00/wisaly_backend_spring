package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.dtos.queries.Blog.BlogComplete
import com.wisaly.wisaly_kotlin_spring.dtos.queries.Blog.BlogsAll
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

    fun findByPagelink(link:String):Blog?

    @Modifying
    @Query("UPDATE blog  SET archived = TRUE WHERE id=?1 AND author_id=?2",nativeQuery = true)
    fun updateBlogArchived(blogId:Long, author_id:Long)

    @Modifying
    @Query("INSERT INTO blog_categories(blog_id, category_index) VALUES (?1,?2)", nativeQuery = true)
    fun addCategories(blogId:Long, category:String)

//    @Modifying
//    @Query("INSERT INTO tag_map(blogs_id, tag_name, type) VALUES (?1,?2,'blog')", nativeQuery = true)
//    fun addTags(blogId:Long, tag:String)

    @Query("SELECT B.id,B.title, B.page_link, B.created_at, B.description, " +
            "B.title_pic FROM blog B WHERE B.archived = FALSE ORDER BY B.created_at DESC ",nativeQuery = true)
    fun getAllNonArchivedBlogs():List<BlogsAll?>

    @Query("SELECT B.id,B.title, B.page_link, B.created_at, " +
            "B.description, concat(U.first_name, U.last_name) AS full_name, U.user_page_link," +
            "U.display_pic as profile_pic, U.id as user_id, " +
            "B.title_pic FROM blog B JOIN wisaly_user U on B.author_id = U.id WHERE B.page_link = ?1",nativeQuery = true)
    fun getCompleteBlog(blog_link:String):BlogComplete?

    @Query("SELECT CAST(COUNT(*) AS integer) FROM like_blog WHERE blog_id=?1",nativeQuery = true)
    fun getLikes(blogId: Long):Int

    @Query("SELECT CAST(CAST(COUNT(*) AS int4 ) AS BOOLEAN) FROM like_blog WHERE user_id=?1 AND blog_id=?2 ",nativeQuery = true)
    fun likedByUser(userId:Long, blogId: Long):Boolean

    @Query("INSERT INTO like_blog(user_id, blog_id) VALUES (?1,?2)",nativeQuery = true)
    fun addLike(userId:Long, blogId: Long)

//    @Query("SELECT B.id,B.title, B.page_link, B.created_at, " +
//            "B.description," +
//            "B.title_pic FROM blog B WHERE B.author_id =?1",nativeQuery = true)
//    fun getUserBlogList(user_id:Long):List<BlogComplete?>
}