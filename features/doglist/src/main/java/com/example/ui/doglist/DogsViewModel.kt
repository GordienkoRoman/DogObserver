package com.example.ui.doglist

import com.example.repository.DogArticlesRepository
import com.example.State
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.utils.models.DogArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.data.local.RoomArticlesRepository
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class DogsViewModel @Inject constructor(
    private val repository: DogArticlesRepository,
    private val roomArticlesRepository: RoomArticlesRepository
    // dogApiImgService: DogApiImgService,
) : ViewModel() {
    val state = MutableLiveData<State>(State.DefaultState())

    private val _dogArticleList = mutableListOf<DogArticle>()
    val dogArticleList
        get() = _dogArticleList

    fun loadData() {
        state.value = State.LoadingState()
        _dogArticleList.add(DogArticle())
        state.value = State.LoadedItemState(DogArticle(facts = mutableListOf("loading...")))
        viewModelScope.launch {
            getItem()
        }

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

    }

    private fun updateItem(dogArticle: DogArticle) {
        if (dogArticle.facts != null && dogArticle.facts.size == 1) {
            _dogArticleList[_dogArticleList.size - 1].facts = dogArticle.facts
            state.postValue(State.LoadedFactsState(_dogArticleList[_dogArticleList.size - 1]))
        } else {
            _dogArticleList[_dogArticleList.size - 1].url = dogArticle.url
            state.postValue(State.LoadedImgState(_dogArticleList[_dogArticleList.size - 1]))
        }
    }


    fun insertItem(position: Int, path: String) {
        viewModelScope.launch {
            roomArticlesRepository.insertArticle(
                DogArticle(url = path,
                    facts = _dogArticleList[position].facts,
                    isFavourite = !_dogArticleList[position].isFavourite
                )
            )
        }

    }

    class DogsViewModelFactory @Inject constructor(
        private val repository: DogArticlesRepository,
        private val roomArticlesRepository: RoomArticlesRepository
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(DogsViewModel::class.java)) {
                DogsViewModel(this.repository, this.roomArticlesRepository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }

    }
}