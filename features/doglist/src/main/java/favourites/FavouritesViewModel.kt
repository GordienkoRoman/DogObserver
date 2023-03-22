package favourites

import Repository.DogArticlesRepository
import State
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.doglist.DogsViewModel
import com.example.utils.models.DogArticle
import kotlinx.coroutines.launch
import room.RoomArticlesRepository
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val roomArticlesRepository: RoomArticlesRepository) :
    ViewModel() {
    val state = MutableLiveData<State>(State.DefaultState())
    // TODO: Implement the ViewModel
    fun onGoToImdbClicked() {

    }
    fun loadItems()
    {
        viewModelScope.launch {
            state.value = State.LoadedState(roomArticlesRepository.loadArticles())
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
