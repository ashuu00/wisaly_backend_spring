package com.wisaly.wisaly_kotlin_spring.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.wisaly.wisaly_kotlin_spring.dtos.BasicUser
import com.wisaly.wisaly_kotlin_spring.dtos.client.feedbackDto
import com.wisaly.wisaly_kotlin_spring.dtos.loginDto
import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.UserDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.BasicUserDto
import com.wisaly.wisaly_kotlin_spring.models.Feedback

import com.wisaly.wisaly_kotlin_spring.repository.*
import com.wisaly.wisaly_kotlin_spring.repository.Product.ProductRepository
import com.wisaly.wisaly_kotlin_spring.utils.JsonMapper
import com.wisaly.wisaly_kotlin_spring.utils.Slugify
import com.wisaly.wisaly_kotlin_spring.utils.TimeDifferenceToString
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.BlogMapper
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.UserMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException
import java.sql.Timestamp
import java.time.Instant

@Service
class UserService(
    val userRepository: UserRepository,
    val blogRepository: BlogRepository,
    val imageRepository: ImageRepository,
    val productRepository: ProductRepository,
    val blogMapper: BlogMapper,
    val userMapper: UserMapper,
    val feedBackRepository: FeedBackRepository
) {
    fun saveUser(user: loginDto): UserDto {
        val makeProfileLink = Slugify.SlugifyString("${user.first_name} ${user.last_name}")
        val newUser = UserDto(
            first_name = user.first_name,
            last_name = user.last_name,
            display_pic = user.display_pic,
            email = user.email,
            created_at = Timestamp.from(Instant.now()),
            updated_at = Timestamp.from(Instant.now()),
            profile_link = makeProfileLink
        )
        val updatedUser = userRepository.save(userMapper.toEntity(newUser))
        return userMapper.fromEntity(updatedUser)
    }

    fun getBasicDetails(user_id:Long):BasicUserDto{
        return userMapper.getBasicUserDto(userRepository.findById(user_id).orElseGet { null }?:throw IllegalStateException("No user found"))
    }

    fun updateUser(user_id: Long?,details:UserDto):UserDto{
        if(details.id!=user_id||user_id==null)throw IllegalStateException("User not authorized");
        val user = userMapper.toEntity(details);
        val updatedUser = userRepository.save(user);
        return userMapper.fromEntity(updatedUser);
    }

    fun manageUser(user: loginDto): BasicUser {
        /** This is where the let helps to perform operations, without expanding */
        val getUser = userRepository.findUserByEmail(user.email)
        //  val myBasicUser = BasicUser
        return getUser?: userRepository.saveBasicUser(
            user.first_name,
            user.last_name,
            user.email,
            user.display_pic,
        )
    }

    fun getUserBlogs(user_id: Long): String {
        return JsonMapper.convertJson(userRepository.getUserBlogs(user_id))
    }

    @Transactional
    fun getUserDetails(user_id: Long,username: String?): String {
        val getDetails = username?.let { userRepository.findByUsername(it)}?:throw IllegalStateException("No user for username found")
        val followersNumber = userRepository.getFollowersFollowingNum(getDetails.id)
        println("followNumbers are ${followersNumber}")
        val updateDetails = userMapper.fromEntity(getDetails)
        updateDetails.following = followersNumber[0]
        updateDetails.followers = followersNumber[1]
        updateDetails.interests = getDetails.interests.map{it.category} as MutableList
        updateDetails.role = if(user_id==getDetails.id)"owner" else "user"
        return JsonMapper.convertJson(updateDetails)
    }

    /** TODO Add this to the blogs service, and remove the jackson mapper, as it is already converted to json in response entity*/
    fun getBlogs(user_id: Long): String {
        val jsonMapper = jacksonObjectMapper()
        val allBlogs = userRepository.getUserBlogs(user_id).map { blog -> blogMapper.fromUserBlogs(blog) }
        //checking the likes for each user blog
        allBlogs.forEach { blog ->
            when (userRepository.checkBlogLikedByUser(user_id, blog.id).liked) {
                0 -> blog.liked = false
                else -> blog.liked = true
            }
        }
        return jsonMapper.writeValueAsString(allBlogs)
    }

    fun suggestusers(user_id: Long?): List<BasicUserDto> {
        /** suggestedusers already have limit to get selected users*/
        println("Sugggested Users called")
        val users = userRepository.suggestedUsers()

        val myUsers = mutableListOf<BasicUserDto>()
        users.filter { it.id != user_id }
        users.forEach {
            val currUser = userMapper.fromSuggested(it)
            currUser.created = TimeDifferenceToString.convertToDate(it.created_at)
            myUsers.add(currUser)
        }
        return myUsers
    }


    fun getDetailsForCard(user_id: Long): String {
        //val images = mediaRepository.
        val blogs = userRepository.getUserBlogs(user_id)
        return JsonMapper.convertJson(blogs)
    }

    fun setRelationship(follower_id: Long, leader_id: Long): String {
        val leader = userRepository.findById(leader_id).orElseGet(null)
            ?: throw IllegalStateException("No user found for the ids")
        val follower = userRepository.findById(follower_id).orElseGet(null)
            ?: throw IllegalStateException("No user found for the ids")
        // val relationship = Follower(lead_user = leader, following_user = follower)
        leader.followers.add(follower)
        follower.following.add(leader)
        val res = userRepository.saveAll(mutableListOf(leader, follower))
        return "Added relationship"
    }

    fun findEmail(email: String): String {
        return JsonMapper.convertJson(userRepository.findByEmail(email)?.blogs ?: "Nothing found")
    }

    fun getFollowing(user_id: Long): String {
        return JsonMapper.convertJson(userRepository.getFollowing(user_id) ?: mutableListOf<Long>())
    }

    fun searchUser(name: String): MutableList<BasicUserDto> {
        val users = userRepository.findByFirstnameStartingWithOrLastnameStartingWith(name, name)
        val userDetails = mutableListOf<BasicUserDto>()
        users?.forEach { userDetails.add(userMapper.getBasicUserDto(it)) }
        return userDetails;
    }

    fun addFeedBack(user_id: Long, feedback: feedbackDto): String {
        val user = userRepository.findById(user_id).orElseGet(null) ?: throw IllegalStateException("No user found")
        val myFeedback = Feedback(message = feedback.message, rating = feedback.rating, user = user)

        user.feedbacks.add(myFeedback)
        userRepository.save(user);

        return "Added Feedback Successfully"
    }

    fun getFollowers(user_id: Long):String{
        val user =  userRepository.findById(user_id).orElse(null)?: throw IllegalStateException("No user for the given id")
        return JsonMapper.convertJson(user.followers)
    }

}