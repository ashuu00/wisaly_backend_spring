package com.wisaly.wisaly_kotlin_spring.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.wisaly.wisaly_kotlin_spring.dtos.queries.DummyPic
import com.wisaly.wisaly_kotlin_spring.service.MediaService
import com.wisaly.wisaly_kotlin_spring.dtos.uploadImageDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/v1/upload")
class MediaController(private val mediaService: MediaService) {

//    //saving image to the database
//    @PostMapping("saveimage")
//    fun uploadExplore(@RequestBody body: uploadImageDto, @RequestAttribute("user_id") user_id: Long?) {
//        this.mediaService.saveUserPhoto(body, user_id!!)
//    }

    //receive image link in response as {id:"", link:""}
    /**This method will save image with username value*/
    @PostMapping("images")
    fun uploadS3(
        @RequestAttribute("user_id") user_id: Long?,
        @RequestBody images: List<MultipartFile>,
        @RequestParam("bucket") bucket: String?
    ): ResponseEntity<String> {

        println("Received Image")
        /** if bucket not defined, save in defualt, otherwise in the given bucket*/
        val result = if (bucket.isNullOrEmpty())  mediaService.saveToS3(images,user_id)
        else this.mediaService.savePhotoInBucket(images, bucket, user_id)
        return ResponseEntity.ok(result);
    }


    /**This just sends the link **/
    @PostMapping("blogsimage")
    fun uploadSingle(
        @RequestBody upload: MultipartFile): String {
        //if(upload.size>1) throw IllegalStateException("Only add one image at a time")
        val myList = listOf(upload)
        val url = this.mediaService.savePhotoInBucket(myList,"wisaly-s3-test",null)

        return url;
    }

    @PostMapping("/uploadmedia")
    fun uploadMediaSingle(
        @RequestBody upload: MultipartFile): String {
        //if(upload.size>1) throw IllegalStateException("Only add one image at a time"
        val myList = listOf(upload)
        val url = this.mediaService.savePhotoInBucket(myList,"wisaly-s3-test",null)

        return url;
    }



    @PostMapping("/dummy")
    fun addDummy(
        @RequestParam("type") type: String?,
        @RequestAttribute("user_id") user_id: Long?,
        @RequestBody sourceClass:DummyPic
    ): ResponseEntity<String> {
        val uploadUserId = user_id ?: 4
        println("Pic sourcelink is $sourceClass and after filter ${sourceClass.source}")
        return ResponseEntity.ok(mediaService.addDummy(sourceClass.source,type,uploadUserId))

    }
}