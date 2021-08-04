package com.wisaly.wisaly_kotlin_spring.repository

import com.wisaly.wisaly_kotlin_spring.dtos.queries.explore_card.BasicExplore
import com.wisaly.wisaly_kotlin_spring.dtos.queries.explore_card.ExploreBlogs
import com.wisaly.wisaly_kotlin_spring.dtos.queries.explore_card.ExploreLikes
import com.wisaly.wisaly_kotlin_spring.dtos.queries.explore_card.ExploreMedia
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.ExploreCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Repository
interface ExploreRepository : JpaRepository<ExploreCard, Long> {
//    @Query("",nativeQuery = true)
//    fun getAllCards()

    @Query("SELECT COUNT(*)::int4::BOOLEAN FROM explore_card EC WHERE EC.id=?1 AND EC.user_id =?2", nativeQuery = true)
    fun checkCardOwner(card_id:Long, user_id: Long):Boolean


//    @Query("INSERT INTO explore_card(archived, created_at, description, title, updated_at, user_id, card_page_link) " +
//            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7) returning id", nativeQuery = true)
//    fun addBasicExplore(archived: Boolean,
//                        created_at: Timestamp,
//                        description: String,
//                        title: String,
//                        updated_at:Timestamp,
//                        user_id:Long,
//                        card_page_link: String):Long

    @Modifying
    @Query("INSERT INTO image_cards(images_id, cards_id) VALUES (?1,?2)", nativeQuery = true)
    fun addImages(image_id: Long, card_id: Long)

    @Modifying
    @Query("INSERT INTO video_cards(videos_id, cards_id) VALUES (?1,?2)", nativeQuery = true)
    fun addVideos(video_id: Long, card_id: Long)

    @Modifying
    @Query("INSERT INTO blog_cards(blogs_id, cards_id) VALUES (?1,?2)", nativeQuery = true)
    fun addBlogs(blog_id: Long, card_id: Long)

    @Modifying
    @Query("INSERT INTO explore_card_categories(category_name, cards_id) VALUES (?1,?2)", nativeQuery = true)
    fun addCategories(category_name: String, card_id: Long)

//    @Query("SELECT *, concat(WS.first_name||' '||WS.last_name) AS full_name, WS.display_pic " +
//            "    AS profile_pic, WS.user_page_link AS user_link " +
//            "FROM explore_card E JOIN (SELECT U.id AS USERID, U.first_name, U.last_name, U.user_page_link, U.display_pic FROM wisaly_user U)WS " +
//            "    ON  E.user_id = WS.USERID WHERE E.card_page_link= ?1",nativeQuery = true)
//    fun getExploreByLink(card_link: String):BasicExplore

    fun findByLink(link:String):ExploreCard?

    @Query("SELECT b.id as id, b.description as description, b.title_pic as picture, b.title as title, b.created_at as created, b.page_link as link FROM blog b join blog_cards bc on b.id = bc.blogs_id WHERE bc.cards_id=?1 AND b.archived = false",nativeQuery = true)
    fun getExploreBlogs(card_id: Long):List<ExploreBlogs>

    @Query("SELECT b.id, ic.order,  b.link FROM image b join image_cards ic on b.id = ic.images_id WHERE ic.cards_id=?1 AND b.archived = false",nativeQuery = true)
    fun getExploreImages(card_id: Long):List<ExploreMedia>

    @Query("SELECT b.id, vc.order,  b.link FROM video b join video_cards vc on b.id = vc.videos_id WHERE vc.cards_id=?1 AND b.archived = false",nativeQuery = true)
    fun getExploreVideos(card_id: Long):List<ExploreMedia>


    @Query("SELECT ec.category_name FROM explore_card_categories ec WHERE ec.cards_id = ?1 ",nativeQuery = true)
    fun getExploreCategories(card_id: Long):String

    @Query("SELECT  CAST(COUNT(L.author_id) AS INTEGER) AS likes FROM like_card L WHERE L.card_id=?1 ", nativeQuery = true)
    fun getLikes(card_id: Long):Int

    @Modifying
    @Query("INSERT INTO like_card(author_id, card_id) VALUES (?1,?2)",nativeQuery = true)
    fun likeCard(user_id: Long, card_id: Long)

    @Modifying
    @Query("DELETE  FROM like_card WHERE author_id=?1 and card_id=?2",nativeQuery = true)
    fun unlikeCard(user_id: Long, card_id: Long)

    @Query("SELECT CAST(CAST(COUNT(*) AS int4) AS BOOLEAN)  FROM like_card L WHERE L.card_id=?1 AND L.author_id=?2",nativeQuery = true)
    fun likedByUser(post_id:Long, user_id:Long):Boolean


}