package com.wisaly.wisaly_kotlin_spring.dtos.queries

import java.sql.Timestamp

interface TagFull {
    var tag_name:String
    var create_at: Timestamp
}