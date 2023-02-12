package Repository

import com.example.utils.models.DogArticle
import retrofit2.Call


interface DogArticlesRepository {
    fun getDogFact(): Call<DogArticle>
    fun getDogImg(): Call<DogArticle>

}
