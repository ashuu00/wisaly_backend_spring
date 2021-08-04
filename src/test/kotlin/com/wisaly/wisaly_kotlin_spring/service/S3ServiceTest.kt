package com.wisaly.wisaly_kotlin_spring.service

import com.amazonaws.AmazonServiceException
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.*
import io.mockk.*

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Disabled

import org.springframework.web.multipart.MultipartFile
import java.io.File

import java.lang.IllegalStateException

import java.io.InputStream

internal  class S3ServiceTest (){


    var image1= mockk<MultipartFile>()
     var amazons3=mockk<AmazonS3>(relaxed = true);
    var image2=mockk<MultipartFile>()
    val mockFile= mockk<File>(relaxed = true)
    val name = "ashutosh"
    private val s3Service = S3Service(amazons3)
    val s3Spy = spyk(S3Service(amazons3))
    val mockS3Object= mockk<S3Object>()

    @Test
    fun `getting all buckets`() {
        val ans = s3Service.getAllBucket()
        verify{ amazons3.listBuckets() }
    }

//    @BeforeEach
//    fun setUp() {
//        amazons3= mockk<AmazonS3>()
//    }
//
//    @AfterEach
//    fun destory(){
//
//    }

    @Test
    fun `saved data successfully`() {
        every { amazons3.putObject(any()) } returns PutObjectResult()
        s3Service.save(name,"cool", mockFile)
        verify{amazons3.putObject(any())}

    }

    @Test
    fun `throw AmazonServiceError when saving, when file type is wrong`() {
//
        every {amazons3.putObject(any())}.throws(AmazonServiceException(""))
        assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy { s3Service.save("ashu", name, mockFile)}.withMessageMatching("Problem in AWS Service")

     // verify{ amazons3.putObject(failObject) }
    }

    @Test
    fun `file downloaded successfully`() {
        every {mockS3Object.objectContent} returns S3ObjectInputStream(InputStream.nullInputStream(),null)
        every {amazons3.getObject(name, name)} returns mockS3Object
        var myFile = s3Service.downloadFile(name,name)
        verify {amazons3.getObject(name, name)}
        //every {fakeService.to} returns ByteArray(20)
        assertThat(myFile).isInstanceOf(ByteArray::class.java)
    }

    @Test
    fun `file deleted from AWS`() {
        every { amazons3.deleteObject(name, name) } returns Unit
        s3Service.deleteFile(name, name)
        verify {amazons3.deleteObject(name, name)}
    }

    @Test
    fun `Image is in correct format`(){
        every {image2.contentType} returns "image/png"
        val checkType = s3Service.isImageEmpty(image2)
        assertThat(checkType).isEqualTo("Image is image/png")

    }

    @Test
    fun `Image is in wrong format`(){
        every {image1.contentType} returns "image/pdf"
        println("${image1.contentType}")
        assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy { s3Service.isImageEmpty(image1) }
    }

    @Test
    fun deleteMultipleFiles() {
        s3Service.deleteMultipleFiles(name, emptyArray())
        verify {amazons3.deleteObjects(any())}
    }

    @Test
    fun `No images while uploading`() {
        assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {  s3Service.uploadImageS3(name, emptyList()) }
    }

    @Test
    fun `Images Uploading to S3`() {
        every{image1.contentType} returns "image/png"
        every{image1.originalFilename} returns "ashu.tosh"
        every { image1.name} returns "imagevro"
       every {s3Spy.convertMultiPartToFile(any())} returns mockFile
        every {s3Spy.save(any(),any(), any())}  returns Unit
        s3Spy.uploadImageS3(name, listOf(image1,image1))
        verify {amazons3.getUrl(any(),any())}
    }

    @Test
    @Disabled
    fun `get Bucket name with the keyname`() {
        val url = "https://walnut-health-tech.s3.ap-south-1.amazonaws.com/a7be1276-0b91-4f39-a580-bee226d20262.png"
        val answer= s3Service.getBucketWithKeyName(url)
       assertThat(answer).containsExactly("walnut-health-tech","a7be1276-0b91-4f39-a580-bee226d20262.png")
//            Assertions.assertThat(bucketName).isEqualTo("walnut-health-tech")
//            Assertions.assertThat(fileName).isEqualTo("a7be1276-0b91-4f39-a580-bee226d20262.png")
//            Assertions.assertThat(returnList).containsExactlyInAnyOrder(bucketName,fileName)

    }

    @Test
    fun `Convert Multipart to form data`(){

    }
    @Test
    @Disabled
    fun createBucket() {
    }
}