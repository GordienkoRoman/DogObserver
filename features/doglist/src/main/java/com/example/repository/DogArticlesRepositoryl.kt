package com.example.repository

import com.example.utils.models.DogArticle
import retrofit2.Response


interface DogArticlesRepository {
    suspend fun getDogFact(): Response<DogArticle>
    suspend fun getDogImg(): Response<DogArticle>

}
