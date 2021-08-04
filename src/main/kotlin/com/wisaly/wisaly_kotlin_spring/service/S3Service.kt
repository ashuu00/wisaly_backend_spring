package com.wisaly.wisaly_kotlin_spring.service

import com.amazonaws.AmazonServiceException
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.Bucket
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectsRequest
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import org.apache.http.entity.ContentType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID
import java.util.Arrays
import kotlin.collections.ArrayList


@Service
class S3Service(private val amazonS3: AmazonS3){

    fun save(
        path: String, fileName: String,
        imageFile: File
    ) {
        try {
            val putObjectRequest = PutObjectRequest(path, fileName, imageFile).withCannedAcl(CannedAccessControlList.PublicRead)
           val answer= amazonS3.putObject(putObjectRequest)
            return
        } catch (err: AmazonServiceException) { // AWS has their own exception handling
            throw java.lang.IllegalStateException("Problem in AWS Service")
        }
    }

    fun downloadFile(path: String?, key: String?): ByteArray? {
        return try {
            val imageObj = amazonS3.getObject(path, key)
            IOUtils.toByteArray(imageObj.objectContent)
        } catch (e: AmazonServiceException) {
            throw IllegalStateException(e)
        } catch (e: IOException) {
            throw IllegalStateException(e)
        }
    }

    fun deleteFile(fullPath: String?, filename: String?) {
        try {
            amazonS3.deleteObject(fullPath, filename)
        } catch (e: AmazonServiceException) {
            throw IllegalStateException(e)
        }
    }

    fun deleteMultipleFiles(bucket: String?, fileLinks:Array<String>){
        try {
           val delRequest = DeleteObjectsRequest(bucket)
               .withKeys(*fileLinks) //spread operator of kotlin
            amazonS3.deleteObjects(delRequest)
        }catch (err:AmazonServiceException){
            System.err.println(err)
            return
        }
    }

    fun uploadImageS3(path: String, images:List<MultipartFile?>):List<String>{

        //check if list not empty, path would be the category title slugified.
        check(images.isNotEmpty()) { "There are no images inside it" }

        val imagesLink: MutableList<String> = ArrayList()
        // now checking for each image and applying queries
        val nonNullImages=images.filterNotNull()
        nonNullImages.forEach {
            //first check if given image type is valid
            isImageEmpty(it)
            try {
                val fileExtension=it.originalFilename?.split(".")?.last()
                val fileName = "${it.name}-${UUID.randomUUID()}.$fileExtension"
                val imageFile = convertMultiPartToFile(it)
                save(path,fileName, imageFile)
                val getUrl = amazonS3.getUrl(path,fileName).toString()
                imagesLink.add(getUrl)
               if(imageFile.delete()){
                   println("Deleted the file")
               }else{
                   println("Unable to delete file")
               }
            } catch (e: IOException) {
                throw IOException()
            }
        }
        return imagesLink;
    }

    //get bucketname and key from link to delete or update in s3
    fun getBucketWithKeyName(url: String): List<String> {
        val bucketName = url.split('.')[0].split("//")[1]
        val files = url.split('/')
        val fileName = files[files.lastIndex]
        return listOf(bucketName, fileName)
    }

    @Throws(IllegalStateException::class)
     fun isImageEmpty (image: MultipartFile):String {
        check(
            Arrays.asList(
                ContentType.IMAGE_JPEG.mimeType,
                ContentType.IMAGE_WEBP.mimeType,
                ContentType.IMAGE_GIF.mimeType,
                ContentType.IMAGE_PNG.mimeType,
                "video/mp4",
                "audio/mp3",
                ContentType.IMAGE_SVG.mimeType
            )
                .contains(image.contentType)
        )  { "Expected an image ${image.contentType}" }
         return "Image is ${image.contentType}"
    }

    //writing all the binary data into complete file
    @Throws(IOException::class)
     fun convertMultiPartToFile(file: MultipartFile): File {
        val convFile = File(file.originalFilename!!)
        val fos = FileOutputStream(convFile)
        fos.write(file.bytes)
        fos.close()
        return convFile
    }

      fun getAllBucket():List<Bucket>{
       val buckets:List<Bucket> = amazonS3.listBuckets()
         //use bucket.getName() for all names of buckets
        return buckets;
    }

     fun createBucket(bucketName:String):String{
            if(amazonS3.doesBucketExistV2(bucketName)){
                return "Error, the bucket already exists"
            }
        amazonS3.createBucket(bucketName);
        return "Bucket $bucketName created successfully"
    }




}