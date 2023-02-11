package com.example.doglist

import Repository.DogArticlesRepository
import State
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utils.models.DogArticle
import restAPI.DogApiFactsService
import restAPI.DogApiImgService
import restAPI.RetrofitCLient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DogsViewModel @Inject constructor (
    private val repository: DogArticlesRepository
   // dogApiImgService: DogApiImgService,
) : ViewModel(){
    val state = MutableLiveData<State>(State.DefaultState())

    private val urlFacts = "https://dog-api.kinduff.com/"
    private val urlImage = "https://random.dog/"
    private val urlImg = "https://i.pinimg.com/564x/15/36/e7/1536e7de67f8f992c595a308ec8ae363.jpg"

    //private var mServiceImg= RetrofitCLient(urlImage).retrofitImageService

    //private var mServiceImg = dogApiImgService
    private val _dogArticleList = mutableListOf<DogArticle>()
    val dogArticleList
         get() =_dogArticleList
    fun loadData(position: Int) {
        state.value = State.LoadingState()
        val dogArticle:DogArticle = repository.getDogArticle()
        _dogArticleList.add(DogArticle("", mutableListOf("123")))
       // _dogArticleList.add(api.getDogArticle())
        state.value = State.LoadedItemState(_dogArticleList[position])
        //getDogArticle(position)
    }



    private fun updateItem(position: Int, dogArticle: DogArticle) {
       /* if(dogArticle.facts!=null&&dogArticle.facts.size==1)
            _dogArticleList[position].facts = dogArticle.facts
        else
            _dogArticleList[position].url = dogArticle.url
    //     if(dogArticle.facts[0]==null)
     //  if(_dogArticleList[position].facts.size!=0&&_dogArticleList[position].url!="")
            state.value = State.LoadedItemState(_dogArticleList[position])*/
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
    class DogsViewModelFactory @Inject constructor(private val repository: DogArticlesRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(DogsViewModel::class.java)) {
               DogsViewModel(this.repository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }


    }
}