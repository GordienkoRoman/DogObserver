package com.example.dog_observer.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dog_observer.State
import com.example.dog_observer.models.DogArticle
import com.example.dog_observer.implementations.DogArticlesLoadImpl
import com.example.dog_observer.restAPI.RetrofitCLient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DogsViewModel(val repository:DogArticlesLoadImpl) : ViewModel(){
    val state = MutableLiveData<State>(State.DefaultState())
    //extension
    // fun <T:Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
    final val urlFacts = "https://dog-api.kinduff.com/"
    final val urlImage = "https://random.dog/"
    var mServiceFact = RetrofitCLient(urlFacts).retrofitService
    var mServiceImg= RetrofitCLient(urlImage).retrofitService
    var dogArticleList = mutableListOf<DogArticle>()
    fun loadData(position: Int) {
        state.value = State.LoadingState()
        dogArticleList.add(DogArticle())
        getDogArticle(position)
    }

    @Inject fun DogsViewModel()
    {

    }

    private fun getDogArticle(position: Int) {
        val t = object : Callback<DogArticle> {
            override fun onResponse(call: Call<DogArticle>, response: Response<DogArticle>) {

                updateItem(position, response.body() as DogArticle)
            }

            override fun onFailure(call: Call<DogArticle>, t: Throwable) {

            }
        }
        mServiceImg.getDogImg().enqueue(t)
        mServiceFact.getDogFact().enqueue(t)
    }

    private fun updateItem(position: Int, dogArticle: DogArticle) {
        if(dogArticle.url==null)
            dogArticleList[position].facts = dogArticle.facts
        else
            dogArticleList[position].url = dogArticle.url
        if(dogArticleList[position].facts!=null&&dogArticleList[position].url!=null)
            state.value = State.LoadedState(dogArticleList)
        }
    fun setFavorite(position: Int)
    {
        state.value=State.LoadingState()
        dogArticleList[position].facts!![0] = "favorite"
        state.value=State.LoadedState(dogArticleList)
    }
    fun onGoToImdbClicked() {
        println("123")
    }
}