package com.example.ui.favourites

import com.example.State
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.utils.models.DogArticle
import kotlinx.coroutines.launch
import com.example.data.local.RoomArticlesRepository
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val roomArticlesRepository: RoomArticlesRepository
) :
    ViewModel() {
    val state = MutableLiveData<State>(State.DefaultState())

    private val _dogFavArticleList = mutableListOf<DogArticle>()
    val dogFavArticleList
        get() = _dogFavArticleList
    fun loadArticles()
    {
        _dogFavArticleList.clear()
        viewModelScope.launch {
            _dogFavArticleList.addAll(roomArticlesRepository.loadArticles())
            state.value = State.LoadedState(_dogFavArticleList)
        }

    }
    fun deleteArticle(position: Int)
    {
        viewModelScope.launch {
            state.value = State.DeletedState(roomArticlesRepository.deleteArticle(_dogFavArticleList[position].facts[0]))
            _dogFavArticleList.removeAt(position)
        }
    }
    class FavouritessViewModelFactory @Inject constructor( private val roomArticlesRepository: RoomArticlesRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(FavouritesViewModel::class.java)) {
                FavouritesViewModel(this.roomArticlesRepository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }

    }
}
