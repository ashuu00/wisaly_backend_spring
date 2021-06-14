package com.wisaly.wisaly_kotlin_spring.utils.objectMappers

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.UserDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.UserDetail
import com.wisaly.wisaly_kotlin_spring.models.user.User
import org.springframework.stereotype.Service

@Service
class UserMapper:Mapper<UserDto, User> {
    override fun fromEntity(entity: User): UserDto = UserDto(
        id = entity.id,
        created_at = entity.created_at,
        updated_at = entity.updated_at,
        email = entity.email,
        first_name = entity.first_name,
        last_name = entity.last_name,
        display_pic = entity.display_pic,
        phone_no = entity.phone_no,
        gender = entity.gender,
        profile_complete = entity.profile_complete,
        roles = entity.roles,
        my_profile = entity.account_owner,
        explore_cards = entity.explore_cards,
        blogs = entity.blogs,
        images = entity.images,
        follower = entity.followers,
        following = entity.following,
        videos = entity.videos,
        comments = entity.comments,

    )

    override fun toEntity(domain: UserDto): User = User(
        id = domain.id,
        created_at = domain.created_at,
        updated_at = domain.updated_at,
        email = domain.email,
        first_name = domain.first_name,
        last_name = domain.last_name,
        display_pic = domain.display_pic,
        phone_no = domain.phone_no,
        gender = domain.gender,
        profile_complete = domain.profile_complete,
        roles = domain.roles,
        explore_cards = domain.explore_cards,
        blogs = domain.blogs,
        images = domain.images,
        followers = domain.follower,
        following = domain.following,
        videos = domain.videos,
        comments = domain.comments
    )

    fun fromDetails(domain: UserDetail):UserDto=UserDto(
        id = domain.id,
        created_at = domain.created_at,
        updated_at = domain.updated_at,
        email = domain.email,
        first_name = domain.first_name,
        last_name = domain.last_name,
        display_pic = domain.display_pic,
        phone_no = domain.phone_no,
        gender = domain.gender,
        profile_complete = domain.profile_complete,
        roles = domain.roles,
    )
}