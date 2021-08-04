package com.wisaly.wisaly_kotlin_spring.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import com.wisaly.wisaly_kotlin_spring.models.user.User
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*
import javax.swing.JOptionPane

@Table(name="category")
@Entity
class Category (
    /* these are the auto-generated values*/
    @Id
    @Column(name="category_name", unique = true, columnDefinition = "VARCHAR(80)")
    var category: String,

    @Column
    var created_at: Timestamp = Timestamp.from(Instant.now()),


    /* end of the auto-generated values*/

    @ManyToMany(fetch= FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(name="explore_card_categories",joinColumns = [JoinColumn(name="category_name")],
    inverseJoinColumns = [JoinColumn(name="cards_id")])
    @JsonIgnore
    var cards:MutableList<ExploreCard> = mutableListOf(),

    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(name="blog_categories",joinColumns = [JoinColumn(name="category_index")],
        inverseJoinColumns = [JoinColumn(name="blog_id")])
    @JsonIgnore
    var blogs:MutableList<Blog> = mutableListOf(),

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="user_interests")
    var users:MutableList<User> = mutableListOf()


)