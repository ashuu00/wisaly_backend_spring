package com.wisaly.wisaly_kotlin_spring.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.wisaly.wisaly_kotlin_spring.dtos.ExploreCardClientDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.DummyPic
import com.wisaly.wisaly_kotlin_spring.service.MediaService
import com.wisaly.wisaly_kotlin_spring.dtos.uploadImageDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/v1/upload")
class MediaController(private val mediaService: MediaService) {

    //saving image to the database
    @PostMapping("saveimage")
    fun uploadExplore(@RequestBody body: uploadImageDto, @RequestAttribute("user_id") user_id: Long?) {
        this.mediaService.saveUserPhoto(body, user_id!!)
    }

    @PostMapping("image")
    fun uploadS3(
        @RequestBody images: List<MultipartFile>,
        @RequestParam("bucket") bucket: String?
    ): ResponseEntity<List<String?>> {

        println("Received Image")
        val result = if (bucket.isNullOrEmpty()) this.mediaService.saveToS3(images)
        else this.mediaService.savePhotoInBucket(images, bucket)
        return ResponseEntity.ok(result);
    }

    @PostMapping("singleimage")
    fun uploadSingle(@RequestBody upload: MultipartFile): String {
        //if(upload.size>1) throw IllegalStateException("Only add one image at a time")
        val myList = listOf(upload)
        val url = this.mediaService.saveToS3(myList)[0]
        val myJson = """{
                |"uploaded":true,
                |"url":"$url"
                |}""".trimMargin()
        return myJson
    }

    @PostMapping("/checkJson") //just for testing
    fun check(@RequestBody content: String): ResponseEntity<String> {
        val mapper = ObjectMapper().registerModule(KotlinModule())

        data class Movie(
            var content: String
        )

        val getJson: Movie = mapper.readValue(content)
        return ResponseEntity.ok("Got ${getJson.content}")
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