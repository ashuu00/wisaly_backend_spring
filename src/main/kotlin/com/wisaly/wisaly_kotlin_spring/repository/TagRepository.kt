package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.dtos.queries.TagFull
import com.wisaly.wisaly_kotlin_spring.dtos.queries.TagOnlyName
import com.wisaly.wisaly_kotlin_spring.models.Keyword
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TagRepository: JpaRepository<Keyword,String> {


    @Query("SELECT * FROM tag T WHERE T.tag_name=?1",nativeQuery = true)
    fun getByTagName(tag:String):TagFull

    @Query("SELECT T.TAG_NAME AS tag_name FROM TAG T WHERE T.TAG_NAME=?1",nativeQuery = true)
    fun getTagByBlogId(tag:String):TagOnlyName


}