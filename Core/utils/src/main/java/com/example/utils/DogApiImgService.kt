package com.example.utils
import com.example.utils.models.DogArticle
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/*interface DogApiImgService {
    // interface DogApiImgService {

    @GET("woof.json")
    fun getDogImg(): Call<DogArticle>
    // }
    *//* interface DogApiFactsService {
        @GET("api/facts")
        fun getDogFact(): Call<DogArticle>

    }*//*
}
    fun  DogApiImgService():DogApiImgService
    {
        return Retrofit.Builder()
            .baseUrl("https://random.dog/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(DogApiImgService::class.java)
    }*/
