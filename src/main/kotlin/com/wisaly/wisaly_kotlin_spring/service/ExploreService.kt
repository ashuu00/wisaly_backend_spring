package com.wisaly.wisaly_kotlin_spring.service

import com.wisaly.wisaly_kotlin_spring.dtos.ExploreCardClientDto
import com.wisaly.wisaly_kotlin_spring.dtos.mappersDto.ExploreDto
import com.wisaly.wisaly_kotlin_spring.models.Category
import com.wisaly.wisaly_kotlin_spring.models.Image
import com.wisaly.wisaly_kotlin_spring.models.Video
import com.wisaly.wisaly_kotlin_spring.models.blog.Blog
import com.wisaly.wisaly_kotlin_spring.repository.*
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.ExploreCardMapper
import org.springframework.stereotype.Service

@Service
class ExploreService(val userRepository: UserRepository,
                     val imageRepository: ImageRepository,
                     val exploreRepository: ExploreRepository,
                     val blogRepository: BlogRepository,
                     val categoryRepository: CategoryRepository,
                     val videoRepository: VideoRepository,
                     val exploreCardMapper: ExploreCardMapper,) {

    //all the ids of saved articles are already present, so just them in a explore-card
    fun saveExplore(exploreClient: ExploreCardClientDto): ExploreDto {
        val photoArray:MutableList<Image> = mutableListOf()
        val videosArray:MutableList<Video> = mutableListOf()
        lateinit var categoryArray:MutableList<Category>
        val blogsArray:MutableList<Blog> = mutableListOf()

        exploreClient.photos.forEach{
            val getPhoto= imageRepository.findById(it.toLong()).orElse(null)?: throw Exception("No photo stored")
            photoArray.add(getPhoto)
        }
        exploreClient.videos.filterNotNull().forEach{
            val getVideo = videoRepository.findById(it.toLong()).orElse(null)?: throw Exception("No video stored")
            videosArray.add(getVideo)
        }

        exploreClient.blogs.filterNotNull().forEach{
            val getBlog=blogRepository.findById(it.toLong()).orElse(null)?: throw Exception("No photo stored")
            blogsArray.add(getBlog)
        }

        exploreClient.categories.forEach{
            val getCategory=categoryRepository.findById(it.toLong()).orElse(null)?: throw Exception("No photo stored")
            categoryArray.add(getCategory)
        }
        val (card_id, card_title, description, archived) = exploreClient
        val author= userRepository.findById(exploreClient.user_id!!).orElse(null)?: throw Exception("No user found for this explore card")

        val newExploreCard = ExploreDto(
            card_title=card_title,
            description = description,
            archived = archived,
            entityBlogs = blogsArray,
            author = author,
            entityCategories = categoryArray,
            card_id = card_id,
        )

        newExploreCard.author = author
        val saveExplore = exploreRepository.save(exploreCardMapper.toEntity(newExploreCard));

        return exploreCardMapper.fromEntity(saveExplore)
    }
}