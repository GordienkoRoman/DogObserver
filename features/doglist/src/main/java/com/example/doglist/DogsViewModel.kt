package com.example.doglist

import Repository.DogArticlesRepository
import State
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utils.models.DogArticle
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
        _dogArticleList.add(DogArticle("", mutableListOf()))
        getItem()
       // _dogArticleList.add(api.getDogArticle())
        //getDogArticle(position)
    }


    private fun getItem(){
        val responseFact = repository.getDogFact()
        val responseImg = repository.getDogImg()
        val callback = object : Callback<DogArticle> {
            override fun onResponse(call: Call<DogArticle>, response: Response<DogArticle>) {
               updateItem( response.body() as DogArticle)
            }

            override fun onFailure(call: Call<DogArticle>, t: Throwable) {
               updateItem( DogArticle(urlImg,mutableListOf()))
            }
        }
        responseFact.enqueue(callback)
        responseImg.enqueue(callback)
        //mServiceImg.getDogImg().enqueue(t)
        /*  dogApiFactsService.getDogFact().enqueue(t)
      return DogArticle("", mutableListOf())*/
    }

    private fun updateItem( dogArticle: DogArticle) {
        if(dogArticle.facts!=null&&dogArticle.facts.size==1)
           _dogArticleList[_dogArticleList.size-1].facts = dogArticle.facts
        else
            _dogArticleList[_dogArticleList.size-1].url = dogArticle.url
    //     if(dogArticle.facts[0]==null)
       if(_dogArticleList[_dogArticleList.size-1].facts.size!=0&&_dogArticleList[_dogArticleList.size-1].url!="")
            state.value = State.LoadedItemState(_dogArticleList[_dogArticleList.size-1])
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