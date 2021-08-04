package com.wisaly.wisaly_kotlin_spring.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.sql.Timestamp
import java.time.Instant

class TimeDifferenceToString {
    companion object {
        @JvmStatic
        fun convertToDate(time: Timestamp): String {
            val currTime = Timestamp.from(Instant.now()).time
            val startTime = time.time
            val difference = currTime - startTime
            var differenceSeconds = (difference / 1000) % 60
            val differenceMinutes = difference / (1000 * 60)
            val differenceHours = difference / (1000 * 60 * 60)
            val differenceDays = difference / (1000 * 60 * 60 * 24)
            val differenceWeeks = difference / (1000 * 3600 * 24 * 7)
            val differenceMonths = difference / (1000L * 3600 * 24 * 30)
            val differenceYears = difference / (1000L * 3600 * 24 * 365)
            if (differenceMinutes < 60) return if (differenceMinutes > 1) "$differenceMinutes minutes" else "$differenceMinutes minute"
            else if (differenceHours < 24) return if (differenceHours > 1) "$differenceHours hours" else "$differenceHours hour"
            else if (differenceDays < 7) return if (differenceDays > 1) "$differenceDays days" else "$differenceDays day"
            else if (differenceWeeks < 5) return if (differenceWeeks > 1) "$differenceWeeks weeks" else "$differenceWeeks week"
            else if (differenceMonths < 12) return if (differenceMonths > 1) "$differenceMonths months" else "$differenceMonths month"
            else return if (differenceYears > 1) "$differenceYears years" else "$differenceYears year"

        }
    }
}