package com.wisaly.wisaly_kotlin_spring.service
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.wisaly.wisaly_kotlin_spring.dtos.mappersDto.SaveImageDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.media.ImageSave
import com.wisaly.wisaly_kotlin_spring.models.Image
import com.wisaly.wisaly_kotlin_spring.repository.*
import com.wisaly.wisaly_kotlin_spring.utils.JsonMapper
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.SaveImageMapper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import kotlin.IllegalStateException

@Service
class MediaService(val userRepository: UserRepository,
                   val imageRepository: ImageRepository,
                   val exploreRepository: ExploreRepository,
                   val saveImageMapper: SaveImageMapper,
                   val draftImageRepostiory: DraftImageRepostiory,
                   val s3Service: S3Service) {
    //we need to get the link from express server for all the images and their size




    fun saveToS3(images: List<MultipartFile>, user_id: Long?):String{
        if(user_id==null) throw IllegalStateException("User not defined for uploading photo")
        val imageLinks =  s3Service.uploadImageS3("wisaly-s3-test",images)
        val response = mutableListOf<ImageSave>()
        imageLinks.forEach {
            response.add(imageRepository.addImage(it,user_id))

        }
        return JsonMapper.convertJson(response)
    }

    fun saveToCustomBucketS3(images: List<MultipartFile>, bucket: String, user_id:Long?):String{
        if(user_id==null) { //for blog images
            return JsonMapper.convertJson(s3Service.uploadImageS3(bucket,images)[0])
        }
        val imageLinks =  s3Service.uploadImageS3(bucket,images)
        val response = mutableListOf<ImageSave>()
        imageLinks.forEach {
            response.add(imageRepository.addImage(it,user_id))
        }
        return JsonMapper.convertJson(response)
    }

    fun saveToDraft(link: String,user_id:Long){
        val author=this.userRepository.findById(user_id).orElse(null)?: throw IllegalStateException("No user found")
        val photo=Image(author = author, link = link, cards = mutableListOf())
        this.draftImageRepostiory.save(photo)
    }

    fun savePhotoInBucket(images: List<MultipartFile>, bucket: String, user_id: Long?):String{
            return saveToCustomBucketS3 ( images, bucket, user_id)
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