package com.wisaly.wisaly_kotlin_spring.models
import lombok.Getter
import lombok.Setter
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
open class BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id=0


    @Column //must generate current time-stamp
    open var created_at: Timestamp = Timestamp.from(Instant.now())

    @Column
    open var updated_at: Timestamp = Timestamp.from(Instant.now())
}