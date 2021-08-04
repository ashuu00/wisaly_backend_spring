package com.wisaly.wisaly_kotlin_spring.models.user

import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.io.Serializable
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*


 class followerId(
    val lead_user: User,
    val following_user: User,
):Serializable


//this class should be tested for creation of composite primary key
//pass primary keys as
@Entity
@Table(uniqueConstraints = arrayOf(UniqueConstraint(name="follower_following_unique", columnNames = arrayOf("lead_user_id","following_user_id"))))
@IdClass(followerId::class)
 class Follower(


    @ManyToOne()
    @Id
    var lead_user: User ,

    @ManyToOne()
    @Id
    var following_user: User,

    @Column
    var created_at: Timestamp = Timestamp.from(Instant.now())
    )
