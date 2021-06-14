package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.dtos.BasicUser
import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.UserDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.SuggestedUsers
import com.wisaly.wisaly_kotlin_spring.dtos.queries.UserBlogs
import com.wisaly.wisaly_kotlin_spring.dtos.queries.UserDetail
import com.wisaly.wisaly_kotlin_spring.models.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

    @Query(
        "SELECT " +
                "B.id, " +
                "B.blog_title, " +
                "B.blog_description, " +
                "COUNT(L.user_id) AS likes " +
                "FROM wisaly_user U " +
                "JOIN blog B ON U.id = B.author_id " +
                "LEFT JOIN " +
                "like_blog L ON B.id = L.blog_id " +
                "GROUP BY (U.id, B.id, L.user_id) " +
                "HAVING U.id = 4",
        nativeQuery = true
    )
    fun getUserBlogs(user_id: Long): List<UserBlogs>

    @Query("SELECT u.first_name, u.last_name FROM wisaly_user u WHERE u.id =?1", nativeQuery = true)
    fun findUserById(id: Int): BasicUser

    @Query(
        "SELECT u.first_name, u.last_name, u.email, u.display_pic, u.created_at FROM wisaly_user u ORDER BY u.created_at DESC LIMIT 10",
        nativeQuery = true
    )
    fun suggestedUsers(): List<SuggestedUsers>

    @Query("SELECT * FROM wisaly_user WHERE id=?1", nativeQuery = true)
    fun getUserDetails(user_id: Long): UserDetail?


//
//    @Query("",nativeQuery = true)
//    fun getFollowersAndFollowing(user_id: Long)
}