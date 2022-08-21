package com.example.dog_observer.restAPI

import com.example.dog_observer.models.DogArticle
import retrofit2.Call
import retrofit2.http.*

interface DogRetrofitServices {
    @GET("api/facts")
    fun getDogFact(): Call<DogArticle>
    @GET("woof.json")
    fun getDogImg():Call<DogArticle>
}