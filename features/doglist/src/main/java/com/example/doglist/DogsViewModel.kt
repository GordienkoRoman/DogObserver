package com.example.doglist

import Repository.DogArticlesRepository
import State
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.utils.models.DogArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import room.RoomArticlesRepository
import javax.inject.Inject


class DogsViewModel @Inject constructor(
    private val repository: DogArticlesRepository,
    private val roomArticlesRepository: RoomArticlesRepository
    // dogApiImgService: DogApiImgService,
) : ViewModel() {
    val state = MutableLiveData<State>(State.DefaultState())

    private val urlImg = "https://i.pinimg.com/564x/15/36/e7/1536e7de67f8f992c595a308ec8ae363.jpg"

    //private var mServiceImg= RetrofitCLient(urlImage).retrofitImageService

    //private var mServiceImg = dogApiImgService
    private val _dogArticleList = mutableListOf<DogArticle>()
    val dogArticleList
        get() = _dogArticleList

    fun loadData(position: Int) {
        state.value = State.LoadingState()
        _dogArticleList.add(DogArticle(urlImg, mutableListOf()))
        state.value = State.LoadedItemState(DogArticle(urlImg, mutableListOf("...")))
        viewModelScope.launch {
            getItem()
        }
        // getItem()
        // _dogArticleList.add(api.getDogArticle())
        //getDogArticle(position)
    }


    private suspend fun getItem() {
        withContext(Dispatchers.IO) {
            val fact = repository.getDogFact()
            if (fact.isSuccessful)
                updateItem(fact.body() as DogArticle)
            val img = repository.getDogImg()
            if (img.isSuccessful)
                updateItem(img.body() as DogArticle)


        }


        //mServiceImg.getDogImg().enqueue(t)
        /*  dogApiFactsService.getDogFact().enqueue(t)
      return DogArticle("", mutableListOf())*/
    }

    private fun updateItem(dogArticle: DogArticle) {
        if (dogArticle.facts != null && dogArticle.facts.size == 1) {
            _dogArticleList[_dogArticleList.size - 1].facts = dogArticle.facts
            state.postValue(State.LoadedFactsState(_dogArticleList[_dogArticleList.size - 1]))
        } else {
            _dogArticleList[_dogArticleList.size - 1].url = dogArticle.url
            state.postValue(State.LoadedImgState(_dogArticleList[_dogArticleList.size - 1]))
        }
        //     if(dogArticle.facts[0]==null)
       /* if (_dogArticleList[_dogArticleList.size - 1].facts.size != 0 && _dogArticleList[_dogArticleList.size - 1].url != "")
        // state.value = State.LoadedItemState(_dogArticleList[_dogArticleList.size-1])
            state.postValue(State.LoadedItemState(_dogArticleList[_dogArticleList.size - 1]))*/
    }

    fun setFavorite(position: Int) {
        //  state.value=State.LoadingState()
        _dogArticleList[position].isFavourite = !_dogArticleList[position].isFavourite
        // state.value=State.LoadedState(_dogArticleList)
    }

    fun onGoToImdbClicked() {
        println("123")
    }
  fun insertItem(position: Int)
    {
        _dogArticleList[position].isFavourite = !_dogArticleList[position].isFavourite
        viewModelScope.launch {
            roomArticlesRepository.insertArticle(_dogArticleList[position])
            val list = roomArticlesRepository.loadArticles()
            list.get(1)
        }

    }

    class DogsViewModelFactory @Inject constructor(private val repository: DogArticlesRepository,private val roomArticlesRepository: RoomArticlesRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(DogsViewModel::class.java)) {
                DogsViewModel(this.repository,this.roomArticlesRepository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }

    }
}