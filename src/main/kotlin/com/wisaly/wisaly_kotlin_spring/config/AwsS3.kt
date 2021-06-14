package com.wisaly.wisaly_kotlin_spring.config
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


//All the AWS configuration would be done here

//All the AWS configuration would be done here
@Configuration
class AwsS3 {
 @Value("\${aws.access_id}")
 private val awsAccessId: String? = null

 @Value("\${aws.access_secret}")
 private val awsAccessKey: String? = null

 @Value("\${aws.region}")
 private val region: String? = null

 // Creating AmazonS3 Instance with Configuration
 @Bean
 fun s3(): AmazonS3 {
  println("aws secret is $awsAccessKey")
  val awsCredentials: AWSCredentials = BasicAWSCredentials(
   awsAccessId,
   awsAccessKey
  )
  return AmazonS3ClientBuilder.standard()
   .withRegion(Regions.AP_SOUTH_1)
   .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
   .build()
 }
}
