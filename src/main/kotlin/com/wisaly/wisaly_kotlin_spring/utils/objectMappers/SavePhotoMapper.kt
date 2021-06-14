package com.wisaly.wisaly_kotlin_spring.utils.objectMappers

import com.wisaly.wisaly_kotlin_spring.dtos.mappersDto.SaveImageDto
import com.wisaly.wisaly_kotlin_spring.models.Image
import org.springframework.stereotype.Service

@Service
class SaveImageMapper :Mapper<SaveImageDto, Image>{
    override fun toEntity(domain: SaveImageDto): Image = Image(
        author= domain.author,
        link = domain.img_link,
        archived = domain.archived,
        cards = mutableListOf()
    )

    override fun fromEntity(entity: Image): SaveImageDto = SaveImageDto(
        photo_id = entity.id,
        author = entity.author,
        img_link = entity.link,
        archived = entity.archived,
        cards = entity.cards
    )
}