package com.wisaly.wisaly_kotlin_spring.services

import com.wisaly.wisaly_kotlin_spring.service.S3Service
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestComponent
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.SpringBootTest


internal class UploadTest() {
    val url = "https://walnut-health-tech.s3.ap-south-1.amazonaws.com/a7be1276-0b91-4f39-a580-bee226d20262.png"
    @Test
    fun `get bucket and keyname`(){
      val bucketName=url.split('.')[0].split("//")[1]
        val files = url.split('/')
        val fileName = files[files.lastIndex]
        val returnList = listOf<String>(bucketName,fileName)
        assertThat(bucketName).isEqualTo("walnut-health-tech")
        assertThat(fileName).isEqualTo("a7be1276-0b91-4f39-a580-bee226d20262.png")
        assertThat(returnList).containsExactlyInAnyOrder(bucketName,fileName)
    }

}