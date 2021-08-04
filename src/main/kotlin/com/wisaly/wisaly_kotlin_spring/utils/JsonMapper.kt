package com.wisaly.wisaly_kotlin_spring.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class JsonMapper {
    companion object{
        @JvmStatic
        fun convertJson(value:Any):String{
            return jacksonObjectMapper().writeValueAsString(value)
        }
    }
}