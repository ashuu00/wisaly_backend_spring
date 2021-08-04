package com.wisaly.wisaly_kotlin_spring.models.product

import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Rating
import com.wisaly.wisaly_kotlin_spring.models.RichTextContent
import com.wisaly.wisaly_kotlin_spring.models.businessPages.Merchant
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    val createdAt: Timestamp = Timestamp.from(Instant.now()),
    var updatedAt: Timestamp = Timestamp.from(Instant.now()),
    @Column(columnDefinition = "varchar(80)")
    var name: String,
    var link: String,
    var about: String,
    var price: Float = 0.0f,
    var discount:Float = 0.0f,
    var stock:Int = 0,
    var inStock:Boolean = false,

    @ManyToOne
    var category: Category,

    @ManyToOne(fetch = FetchType.LAZY)
    var description: RichTextContent,

    var websiteLink: String? = null,


    @ManyToOne(fetch = FetchType.LAZY)
    var seller: Merchant,

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
    var ratings: MutableList<Rating> = mutableListOf(),
) {}