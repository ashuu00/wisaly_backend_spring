package com.wisaly.wisaly_kotlin_spring.repository;

import com.wisaly.wisaly_kotlin_spring.dtos.queries.media.ImageSave
import com.wisaly.wisaly_kotlin_spring.models.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : JpaRepository<Image, Long>{

    @Query("INSERT INTO image(link,author_id) values ( ?1,?2 ) RETURNING *",nativeQuery = true)
    fun addImage(link:String, author:Long):ImageSave

    @Query(" SELECT I.id, I.link,'image' AS TYPE from image I WHERE I.author_id = ?1  " +
            " UNION SELECT V.id, V.link, 'video' AS TYPE FROM video V WHERE V.author_id=?1 ",nativeQuery = true)
    fun getUserImages(user_id:Long)


}
