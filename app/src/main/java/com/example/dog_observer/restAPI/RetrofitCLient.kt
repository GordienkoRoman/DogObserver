package com.example.dog_observer.restAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCLient(url:String) {
    private var retrofit: Retrofit? = null
    private val BASE_URL = url
    val retrofitService: DogRetrofitServices
        get() = getClient(BASE_URL).create(DogRetrofitServices::class.java)
    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}