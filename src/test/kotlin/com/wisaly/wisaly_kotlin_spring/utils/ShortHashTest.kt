package com.wisaly.wisaly_kotlin_spring.utils

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import org.assertj.core.api.Assertions.*

class ShortHashTest {

    private companion object{
        @JvmStatic
        fun numberStream():Stream<Long> = Stream.of(
            12,
            24,
            36,
            48,
            60
        )
        val Hash = ShortHash("Ashu",8)
    }
    @ParameterizedTest
    @MethodSource("numberStream")
    fun `generate hash for all multiples of 12`(num:Long){
        val res = Hash.encode(num)
        println("res is $res")
        assertThat(res).isInstanceOf(String::class.java)
    }

    @Test
    fun `getting hashed output for strings`(){
        val getSlugified = Slugify.SlugifyString("ashuto@?s??{h} co&!!ool")
        assertThat(getSlugified).isInstanceOf(String::class.java)
        println(getSlugified)
        assertThat(getSlugified.split("-")[0]).isEqualTo("ashutosh")
    }
}