package Repository

import com.example.utils.models.DogArticle


interface DogArticlesRepository {
    fun getDogArticle() : DogArticle
}
