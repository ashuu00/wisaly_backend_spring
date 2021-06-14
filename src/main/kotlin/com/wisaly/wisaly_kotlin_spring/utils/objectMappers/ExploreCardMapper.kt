package com.wisaly.wisaly_kotlin_spring.utils.objectMappers

import com.wisaly.wisaly_kotlin_spring.dtos.mappersDto.ExploreDto
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import org.springframework.stereotype.Service

@Service
class ExploreCardMapper:Mapper<ExploreDto, ExploreCard> {
    override fun toEntity(domain: ExploreDto): ExploreCard = ExploreCard(
        user = domain.author!!,
        archived = domain.archived,
        categories = domain.entityCategories,
        images = domain.entityImages,
        videos = domain.entityVideos,
        blogs = domain.entityBlogs,
        title = domain.card_title,
        description = domain.description,
        tags = domain.entityTags,
        id = -1
    )

    override fun fromEntity(entity: ExploreCard): ExploreDto = ExploreDto(
        author = entity.user,
        archived = entity.archived,
        categories = entity.categories.map { it.category_name },
        images = entity.images.map{it.link} as MutableList<String>,
        videos = entity.videos.map{it.link} as MutableList<String>,
        blogIds = entity.blogs.map { it.id },
        description = entity.description,
        card_title = entity.title,
        card_id = entity.id,
        tags = entity.tags.map { it.tag_name }
    )

}