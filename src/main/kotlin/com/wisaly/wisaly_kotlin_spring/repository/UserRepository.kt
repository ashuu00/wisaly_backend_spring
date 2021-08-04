package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.dtos.BasicUser
import com.wisaly.wisaly_kotlin_spring.dtos.queries.Blog.IsLiked
import com.wisaly.wisaly_kotlin_spring.dtos.queries.SuggestedUsers
import com.wisaly.wisaly_kotlin_spring.dtos.queries.Blog.UserBlogs
import com.wisaly.wisaly_kotlin_spring.models.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
    @Query(
         "SELECT " +
                "B.id, " +
                "B.title, B.created_at, B.title_pic, B.archived, " +
                "B.description, B.updated_at, " +
                "B.page_link, " +
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

    fun findByUsername(username: String): User?

    fun findByFirstnameStartingWithOrLastnameStartingWith(firstName:String,lastName:String):List<User>?

    @Query( "SELECT u.first_name,u.id, u.last_name, u.email, u.display_pic, u.username, u.about FROM wisaly_user u WHERE u.email =?1", nativeQuery = true)
    fun findUserByEmail(email: String): BasicUser?

    @Query("SELECT u.id FROM user_relationship r JOIN wisaly_user u on u.id=r.following_id where r.followers_id = ?1", nativeQuery = true)
    fun getFollowing(user_id: Long): MutableList<Long>?

    @Query("INSERT INTO wisaly_user(first_name, last_name, email, display_pic) VALUES (?1,?2,?3,?4) RETURNING * ", nativeQuery = true)
    fun saveBasicUser(FNAME:String,LNAME:String, EMAIL:String, PIC:String): BasicUser

    @Query(
        "SELECT u.id, u.first_name, u.last_name, u.email, u.display_pic, u.created_at, u.user_page_link, u.about, u.username  FROM wisaly_user u ORDER BY u.created_at DESC LIMIT 10",
        nativeQuery = true
    )
    fun suggestedUsers(): List<SuggestedUsers>



    @Query("SELECT COUNT(*) AS liked FROM like_blog L WHERE L.user_id=?1 AND L.blog_id=?2",nativeQuery = true)
    fun checkBlogLikedByUser(user_id: Long,blog_id:Long):IsLiked

    @Query("SELECT COUNT(lead_user_id) AS FOLLOWLEAD FROM follower where lead_user_id=?1 UNION " +
            "SELECT COUNT(lead_user_id) AS FOLLOWLEAD FROM follower where following_user_id=?1 ;",nativeQuery = true)
    fun getFollowersFollowingNum(user_id: Long):List<Int>

    @Query("SELECT * FROM WHERE",nativeQuery = true)
    fun getAllFollowersFollowing()


//
//    @Query("",nativeQuery = true)
//    fun getFollowersAndFollowing(user_id: Long)
}