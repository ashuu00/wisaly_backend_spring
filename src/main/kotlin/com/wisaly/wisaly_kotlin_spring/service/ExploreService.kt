package com.wisaly.wisaly_kotlin_spring.service

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.ExploreCardDto
import com.wisaly.wisaly_kotlin_spring.dtos.queries.explore_card.ExploreComplete
import com.wisaly.wisaly_kotlin_spring.models.exploreCard.Media
import com.wisaly.wisaly_kotlin_spring.repository.*
import com.wisaly.wisaly_kotlin_spring.utils.JsonMapper
import com.wisaly.wisaly_kotlin_spring.utils.Slugify
import com.wisaly.wisaly_kotlin_spring.utils.TimeDifferenceToString
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.ExploreCardMapper
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExploreService(
    val exploreRepository: ExploreRepository,
    val exploreCardMapper: ExploreCardMapper,
    val categoryRepository: CategoryRepository,
    val blogRepository: BlogRepository,
    val userRepository: UserRepository,
    val mediaRepository: MediaRepository,
    val commentService: CommentService
) {

    //all the ids of saved articles are already present, so just them in a explore-card
    @Transactional
    fun saveExplore(exploreClient: ExploreCardDto, user_id: Long):ExploreCardDto {
       // val (title, description, archived, blogs, photos, videos, categories, tags, user_id) = exploreClient
        val myExplore = exploreCardMapper.toEntity(exploreClient)
        myExplore.link = Slugify.SlugifyString(myExplore.title)
        exploreClient.categories.forEach {
            myExplore.categories.add(categoryRepository.findByCategory(it)?:throw IllegalStateException("No category found"))
        }

        exploreClient.blogs.forEach {
            myExplore.blogs.add(blogRepository.findById(it).orElseGet(null)?:throw IllegalStateException("No blogs found!"))
        }

        myExplore.user = userRepository.findById(user_id).orElseGet { null }?: throw IllegalStateException("No user found")

        val savedCard = exploreRepository.save(myExplore)
        // val (card_id, card_title, description, archived) = exploreClient
        exploreClient.images.forEach {
            val myImage = Media(it,savedCard)
            mediaRepository.save(myImage)
            savedCard.medias.add(myImage)
        }
        //val author= userRepository.findById(exploreClient.user_id!!).orElse(null)?: throw Exception("No user found for this explore card")
        return exploreCardMapper.fromEntity(savedCard)
    }

    fun getCompleteCard(card_link:String, user_id:Long?):String{
       // val exploreBasic = exploreCardMapper.fromEntity(exploreRepository.findByLink(card_link)?: throw IllegalStateException("No card found!"))

//        val completeDetails = ExploreComplete(details = exploreBasic,
//                            blogs = exploreRepository.getExploreBlogs(exploreBasic.id),
//                            images = exploreRepository.getExploreImages(exploreBasic.id),
//                            videos = exploreRepository.getExploreVideos(exploreBasic.id),
//                            likes = exploreRepository.getLikes(exploreBasic.id),
//                            likedByUser = liked)
        val exploreEntity =exploreRepository.findByLink(card_link)?: throw IllegalStateException("No card found!")
        val completeDetails = exploreCardMapper.completeCard(exploreEntity)
        val liked = user_id?.let { exploreRepository.likedByUser(completeDetails.id,user_id) }?:false
        completeDetails.likedByUser = liked
        completeDetails.created = TimeDifferenceToString.convertToDate(completeDetails.created_at!!)
        completeDetails.likes  = exploreRepository.getLikes(completeDetails.id)
        completeDetails.comments = commentService.getAllCommentsCard(exploreEntity.comments)
       val completeAns = JsonMapper.convertJson(completeDetails)

        return completeAns
    }

    fun deleteCard(user_id: Long,card_id:Long):String{
        val isAuthorized = exploreRepository.checkCardOwner(card_id, user_id)
        if(isAuthorized) {exploreRepository.deleteById(card_id)
        return "Deleted the user"}
        else{
            return "UnAuthorized"
        }
    }

    fun updateCard(user_id: Long, card_id: Long, details: ExploreCardDto):String{
        val isAuthorized = exploreRepository.checkCardOwner(card_id, user_id)
        if(isAuthorized) {exploreRepository.save(exploreCardMapper.toEntity(details))
            return "Updated the user"}
        else{
            return "UnAuthorized"
        }
    }

    fun getAllCards(page:Int, limit:Int):String
    {
        val allCards = exploreRepository.findAll(PageRequest.of(page, limit)).toList();
        val exploreDtos = allCards.map{card->exploreCardMapper.fromEntity(card)}
        return  JsonMapper.convertJson(exploreDtos)//JsonMapper.convertJson(allCards)
    }

    @Transactional
    fun likeCard(user_id:Long, card_id:Long, isLiked:Boolean):String{
        if(isLiked) {exploreRepository.likeCard(user_id, card_id)
            return "Liked Post Successful"
        }
        else {exploreRepository.unlikeCard(user_id, card_id)
            return "Unliked Post Successful"}

    }


}