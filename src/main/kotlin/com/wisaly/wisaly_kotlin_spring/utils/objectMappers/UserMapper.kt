package com.wisaly.wisaly_kotlin_spring.utils.objectMappers

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.UserDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.BasicUserDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.SuggestedUsers
import com.wisaly.wisaly_kotlin_spring.models.user.User
import org.springframework.stereotype.Service

@Service
class UserMapper:Mapper<UserDto, User> {
    override fun fromEntity(entity: User): UserDto = UserDto(
        id = entity.id,
        created_at = entity.created_at,
        updated_at = entity.updated_at,
        email = entity.email,
        first_name = entity.firstname,
        last_name = entity.lastname,
        username = entity.username,
        display_pic = entity.display_pic,
        phone_no = entity.phone_no,
        gender = entity.gender,
        profile_complete = entity.profile_complete,
        role = entity.role,
        about = entity.about?:"",
        city = entity.city?:"delhi",
        my_profile = entity.accountOwner,
        profile_link = entity.userPageLink

    )

    override fun toEntity(domain: UserDto): User = User(
        id = domain.id,
        created_at = domain.created_at,
        updated_at = domain.updated_at,
        email = domain.email,
        firstname = domain.first_name,
        lastname = domain.last_name,
        display_pic = domain.display_pic,
        phone_no = domain.phone_no,
        gender = domain.gender,
        profile_complete = domain.profile_complete,
        role = domain.role,
        about = domain.about,
        city = domain.city,
        userPageLink = domain.profile_link
    )

    fun fromSuggested(domain: SuggestedUsers):BasicUserDto=BasicUserDto(
        id = domain.id,
        first_name = domain.first_name,
        last_name = domain.last_name,
        display_pic = domain.display_pic,
       username = domain.username,
        about = domain.about,
    )

    fun getBasicUserDto(entity: User):BasicUserDto =BasicUserDto(
        id = entity.id,
        first_name = entity.firstname,
        last_name =entity.lastname,
        username = entity.username,
        about = entity.about?:"",
        display_pic = entity.display_pic,
    )


}