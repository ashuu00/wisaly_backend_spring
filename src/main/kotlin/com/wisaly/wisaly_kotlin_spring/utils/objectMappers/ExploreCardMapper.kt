package com.wisaly.wisaly_kotlin_spring.utils.objectMappers

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.ExploreCardDto
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import org.springframework.stereotype.Service

@Service
class ExploreCardMapper:Mapper<ExploreCardDto, ExploreCard> {
    override fun toEntity(domain: ExploreCardDto): ExploreCard = ExploreCard(
        archived = domain.archived,
        title = domain.title,
        description = domain.description,
    )

    override fun fromEntity(entity: ExploreCard): ExploreCardDto = ExploreCardDto(
        archived = entity.archived,
        title = entity.title,
        description = entity.description,
        id = entity.id,
        updated_at = entity.updated_at,
        created_at = entity.created_at,
        link = entity.link,
        images = (entity.medias.filter{it.isVideo==false}).map { it.link },
        categories = entity.categories.map { it.category}
    )

    fun completeCard(entity: ExploreCard):ExploreCardDto = ExploreCardDto(
        archived = entity.archived,
        title = entity.title,
        description = entity.description,
        id = entity.id,
        updated_at = entity.updated_at,
        created_at = entity.created_at,
        link = entity.link,
        images = (entity.medias.filter{it.isVideo==false}).map { it.link },
        categories = entity.categories.map { it.category},
        authorFullName = "${entity.user?.firstname} ${entity.user?.lastname}",
        authorBio = entity.user?.about?:"",
        authorPic = entity.user?.display_pic?:"",
        authorUsername = entity.user?.username?:"",
    )

}