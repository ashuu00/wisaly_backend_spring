package com.wisaly.wisaly_kotlin_spring.utils

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.sql.Timestamp
import java.time.Instant
import java.util.stream.Stream

class TimeDifferenceToStringTest {
    private companion object{
        @JvmStatic
        fun dateStream(): Stream<Timestamp> = Stream.of(
            Timestamp(Instant.now().toEpochMilli()-200000L),
            Timestamp(Instant.now().toEpochMilli()-(2000000000L/3)),
            Timestamp(Instant.now().toEpochMilli()-21100000L)
        )
    }
    @ParameterizedTest
    @MethodSource("dateStream")
    fun `generate differnce in dates for each parameter`(date:Timestamp){
        val res = TimeDifferenceToString.convertToDate(date)
        println("res is $res")
        Assertions.assertThat(res).isInstanceOf(String::class.java)
    }
}