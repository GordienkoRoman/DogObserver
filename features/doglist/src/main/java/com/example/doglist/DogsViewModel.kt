package com.example.doglist

import Repository.DogArticlesRepository
import State
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utils.DogApiImgService
import com.example.utils.models.DogArticle
import restAPI.RetrofitCLient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DogsViewModel @Inject constructor (
    //repository: DogArticlesRepository,
   // dogApiImgService: DogApiImgService,

) : ViewModel(){
    //val state = MutableLiveData<State>(State.DefaultState())

    private val urlFacts = "https://dog-api.kinduff.com/"
    private val urlImage = "https://random.dog/"
    private val urlImg = "https://i.pinimg.com/564x/15/36/e7/1536e7de67f8f992c595a308ec8ae363.jpg"

   // private var mServiceFact = RetrofitCLient(urlFacts).retrofitFactService
   // private var mServiceImg= RetrofitCLient(urlImage).retrofitImageService

    //private var mServiceImg = dogApiImgService
    private val _dogArticleList = mutableListOf<DogArticle>()
    val dogArticleList
         get() =_dogArticleList
    fun loadData(position: Int) {
      //  state.value = State.LoadingState()
        _dogArticleList.add(DogArticle("", mutableListOf()))
        getDogArticle(position)
        //val result = mServiceFact.getDogFact()
    }

    private fun getDogArticle(position: Int) {
        val t = object : Callback<DogArticle> {
            override fun onResponse(call: Call<DogArticle>, response: Response<DogArticle>) {
                updateItem(position, response.body() as DogArticle)
            }

            override fun onFailure(call: Call<DogArticle>, t: Throwable) {
                updateItem(position, DogArticle(urlImg,mutableListOf()))
            }
        }
        //mServiceImg.getDogImg().enqueue(t)
      //  mServiceFact.getDogFact().enqueue(t)
    }

    private fun updateItem(position: Int, dogArticle: DogArticle) {
        if(dogArticle.facts!=null&&dogArticle.facts.size==1)
            _dogArticleList[position].facts = dogArticle.facts
        else
            _dogArticleList[position].url = dogArticle.url
        // if(dogArticle.facts[0]==null)
       if(_dogArticleList[position].facts.size!=0&&_dogArticleList[position].url!="")
           true
         //   state.value = State.LoadedItemState(_dogArticleList[position])
    }
    fun setFavorite(position: Int)
    {
      //  state.value=State.LoadingState()
        _dogArticleList[position].isFavourite = !_dogArticleList[position].isFavourite
       // state.value=State.LoadedState(_dogArticleList)
    }
    fun onGoToImdbClicked() {
        println("123")
    }
   /* class Factory @Inject constructor(private val dogApiImgService: DogApiImgService) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DogsViewModel(dogApiImgService) as T
        }
    }*/
}