package com.wisaly.wisaly_kotlin_spring.utils.objectMappers

import com.fasterxml.jackson.databind.ObjectMapper
import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.WisalyServiceDto
import com.wisaly.wisaly_kotlin_spring.models.businessPages.ServicePlan
import com.wisaly.wisaly_kotlin_spring.models.businessPages.WisalyService
import org.springframework.stereotype.Service

@Service
class WisalyServiceMapper: Mapper<WisalyServiceDto, WisalyService> {
    override fun toEntity(domain: WisalyServiceDto): WisalyService =WisalyService(
        domain.id,
        domain.createdAt,
        domain.updatedAt,
        domain.title,
        domain.description,
        domain.businessName?:throw IllegalStateException("No user found"),
        domain.ratings,
        domain.plans as MutableList<ServicePlan>
    )


    override fun fromEntity(entity: WisalyService): WisalyServiceDto = WisalyServiceDto(
        entity.id,
        entity.createdAt,
        entity.updatedAt,
        entity.title,
        entity.description,
        entity.businessName,
        entity.ratings,
        entity.plans
    )


    fun basicFromEntity(entity:WisalyService): WisalyServiceDto =WisalyServiceDto(
        entity.id,
        entity.createdAt,
        entity.updatedAt,
        entity.title,
        entity.description,
    )

}