package com.wisaly.wisaly_kotlin_spring.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.wisaly.wisaly_kotlin_spring.dtos.loginDto
import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.UserDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.SuggestedUsers
import com.wisaly.wisaly_kotlin_spring.models.user.User
import com.wisaly.wisaly_kotlin_spring.repository.FollowRepository
import com.wisaly.wisaly_kotlin_spring.repository.UserRepository
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.UserMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException
import java.sql.Timestamp
import java.time.Instant

@Service
class UserService(val userRepository: UserRepository,
                  val followRepository: FollowRepository,
                  val userMapper: UserMapper) {
    fun saveUser(user: loginDto): UserDto {
        val newUser = UserDto(first_name = user.first_name,
            last_name = user.last_name,
            display_pic = user.display_pic,
            email = user.email,
            created_at = Timestamp.from(Instant.now()),
            updated_at = Timestamp.from(Instant.now())
            )
        val updatedUser = userRepository.save(userMapper.toEntity(newUser))
        return userMapper.fromEntity(updatedUser)
    }

    fun manageUser(user: loginDto): UserDto {
        /** This is where the let helps to perform operations, without expanding */
        val getUser: UserDto? = userRepository.findByEmail(user.email)?.let { userMapper.fromEntity(it) }
        return getUser?: saveUser(user)
    }

    @Transactional
    fun getUserDetails(user_id: Long): String {
        val jsonMapper = jacksonObjectMapper()
        return  jsonMapper.writeValueAsString( userMapper.fromDetails(userRepository.getUserDetails(user_id) ?:
        throw IllegalStateException("No user found")))
    }

    fun followUser(user_id:Long, lead_user_id:Long):String{
       //  followRepository.save()
        return "Function called"
    }

    fun getBlogs(user_id: Long):String{
        val jsonMapper = jacksonObjectMapper()
        return jsonMapper.writeValueAsString(userRepository.getUserBlogs(user_id))
    }

    fun suggestusers():List<SuggestedUsers>{
        return userRepository.suggestedUsers()
    }

    fun loginUser(){

    }
}