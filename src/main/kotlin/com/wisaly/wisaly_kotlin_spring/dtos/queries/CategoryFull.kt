package com.wisaly.wisaly_kotlin_spring.dtos.queries

import java.sql.Timestamp

interface CategoryFull {
    val category_name: String
    val created_at: Timestamp
}