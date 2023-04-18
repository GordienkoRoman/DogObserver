package com.example.data.remote

import com.example.utils.models.DogArticle
import retrofit2.Response
import retrofit2.http.*

interface DogApiImgService {

    @GET("woof.json")
    suspend fun getDogImg(): Response<DogArticle>
}
interface DogApiFactsService {

    @GET("api/facts")
    suspend fun getDogFact(): Response<DogArticle>
}
