package com.wisaly.wisaly_kotlin_spring.utils

import kotlin.random.Random

class Slugify {
    companion object {
        val hash = ShortHash("WisalyUrl", 8)

        @JvmStatic
        fun SlugifyString(string: String): String {
            val id = Random(System.nanoTime()).nextInt(1200003).toLong()
            val regSpecial = Regex("[@!?()\\]=^&#}{]")
            val firstFilter = regSpecial.replace(string, "")
            val secondFilter = firstFilter.replace(" ", "-")
            return "${secondFilter.toLowerCase()}-${hash.encode(id)}"
        }

    }
}