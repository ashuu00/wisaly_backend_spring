package com.wisaly.wisaly_kotlin_spring.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.wisaly.wisaly_kotlin_spring.dtos.mappersDto.SaveImageDto
import com.wisaly.wisaly_kotlin_spring.dtos.uploadImageDto
import com.wisaly.wisaly_kotlin_spring.models.Image
import com.wisaly.wisaly_kotlin_spring.repository.*
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.SaveImageMapper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.lang.IllegalStateException

@Service
class MediaService(val userRepository: UserRepository,
                   val imageRepository: ImageRepository,
                   val exploreRepository: ExploreRepository,
                   val saveImageMapper: SaveImageMapper,
                   val draftImageRepostiory: DraftImageRepostiory,
                   val s3Service: S3Service) {
    //we need to get the link from express server for all the images and their size
    fun saveUserPhoto(details : uploadImageDto, user_id:Long): SaveImageDto?{
        val author = userRepository.findById(user_id).orElseGet(null)
        val getPhoto= SaveImageDto(
            photo_id = null,
            img_link = details.img_src,
            archived = false,
            cards = mutableListOf(),
            author = author
            )
        val newPhoto = imageRepository.save(saveImageMapper.toEntity(getPhoto));
        return saveImageMapper.fromEntity(newPhoto);
    }



    fun saveToS3(images: List<MultipartFile>):List<String>{
        return s3Service.uploadImageS3("wisaly-s3-test",images)
    }

    fun saveToCustomBucketS3(images: List<MultipartFile>, bucket: String):List<String>{
        return s3Service.uploadImageS3(bucket,images)
    }

    fun saveToDraft(link: String,user_id:Long){
        val author=this.userRepository.findById(user_id).orElse(null)?: throw IllegalStateException("No user found")
        val photo=Image(author = author, link = link, cards = mutableListOf())
        this.draftImageRepostiory.save(photo)
    }

    fun savePhotoInBucket(images: List<MultipartFile>, bucket: String):List<String> {
            return saveToCustomBucketS3 ( images, bucket )
    }

    fun addDummy(link:String, uploadType:String?, user_id: Long):String{
        when(uploadType){
            "video" -> {return "Nothing found!!"}
            else -> {
                val user = userRepository.findById(user_id).orElse(null)
                val item = SaveImageDto(author= user,img_link = link)
                val myPhoto = imageRepository.save(saveImageMapper.toEntity(item))
                val returnPhoto = saveImageMapper.fromEntity(myPhoto)
                val mapper = jacksonObjectMapper()
                return mapper.writeValueAsString(returnPhoto)
            }//call upload service
        }
    }

   // fun deletePhoto()


//   fun saveBlog(blog:blogUploadDto):Blog{
//       val author = this.userRepository.findById(blog.author.toInt()).orElse(null)?:throw IllegalStateException("No author for the blog")
//       return this.blogRepository.saveBlog(blog, author)
//   }
}