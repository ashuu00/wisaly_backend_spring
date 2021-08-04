package com.wisaly.wisaly_kotlin_spring.models.businessPages
import com.wisaly.wisaly_kotlin_spring.models.Rating
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
class WisalyService (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,
    val createdAt: Timestamp = Timestamp.from(Instant.now()),
    var updatedAt: Timestamp = Timestamp.from(Instant.now()),
    var title:String,
    var description:String,

    /**This should redirect to business page*/
    @ManyToOne
    val businessName: User,

    @OneToMany(mappedBy = "rating", fetch = FetchType.LAZY)
    var ratings:MutableList<Rating>  = mutableListOf(),

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    var plans:MutableList<ServicePlan> = mutableListOf(),





    ){}