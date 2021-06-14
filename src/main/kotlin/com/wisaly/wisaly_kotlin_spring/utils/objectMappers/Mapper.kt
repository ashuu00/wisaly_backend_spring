package com.wisaly.wisaly_kotlin_spring.utils.objectMappers

interface Mapper<D, E> {
    fun toEntity(domain: D):E
    fun fromEntity(entity: E): D
}