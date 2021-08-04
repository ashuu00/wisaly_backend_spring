package com.wisaly.wisaly_kotlin_spring.service

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.RatingDto
import com.wisaly.wisaly_kotlin_spring.repository.UserRepository
import com.wisaly.wisaly_kotlin_spring.repository.WisalyService.WisalyServiceRepository
import org.springframework.stereotype.Service

@Service
class RatingService(private val userRepository: UserRepository,
private val wisalyServiceRepository: WisalyServiceRepository){

    fun addRating(type: String, user:Long, addTo:Long, content: RatingDto){
        //get the rating and store it in database
        val author = userRepository.findById(user)
       lateinit var item:Any
       when (type) {
           "service"->{
               item = wisalyServiceRepository.findById(addTo)
           }
           "product"->println("Added something")
           else -> println("Error in getting the product")
       }

    }

//    fun ratingsQuery(category:Long, type:String, params)

    fun getRecommended(type:String?, item:Long){
        /**use when, when we need for product also **/
        val myType=type?:"service"
        /**Get most recommended, with highest likes from the ratings*/
    }


}