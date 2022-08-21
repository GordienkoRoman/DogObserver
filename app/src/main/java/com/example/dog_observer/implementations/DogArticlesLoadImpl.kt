package com.example.dog_observer.implementations

import com.example.dog_observer.models.DogArticle
import com.example.dog_observer.restAPI.DogRetrofitServices
import com.example.dog_observer.restAPI.RetrofitCLient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogArticlesLoadImpl {
    final val urlFacts = "https://dog-api.kinduff.com/"
    final val urlImage = "https://random.dog/"
    lateinit var mServiceFact: DogRetrofitServices
    lateinit var mServiceImg: DogRetrofitServices
    lateinit var dogArticleList: MutableList<DogArticle>
    fun loadData(){
        mServiceFact = RetrofitCLient(urlFacts).retrofitService
        mServiceImg = RetrofitCLient(urlImage).retrofitService
        dogArticleList = mutableListOf()
        getDogArticle(1)
    }

    private fun getDogArticle(position: Int) {
        val t = object : Callback<DogArticle> {
            override fun onResponse(call: Call<DogArticle>, response: Response<DogArticle>) {

                updateItem(position, dogArticleList, response.body() as DogArticle)
            }

            override fun onFailure(call: Call<DogArticle>, t: Throwable) {

            }
        }
        mServiceImg.getDogImg().enqueue(t)
        mServiceFact.getDogFact().enqueue(t)
    }

    private fun updateItem(
        position: Int,
        list: MutableList<DogArticle>,
        dogArticle: DogArticle
    ) {
        if (position == dogArticleList.size) {
            if (list[position].url == null)
                list[position].url = dogArticle.url
            else
                list[position].facts = dogArticle.facts
        } else
            list.add(dogArticle)
    }
}