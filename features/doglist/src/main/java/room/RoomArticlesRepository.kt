package room

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.example.utils.models.DogArticle
import kotlinx.coroutines.runBlocking
import room.entities.ArticlesEntity
import javax.inject.Inject


class RoomArticlesRepository @Inject constructor(
    private val articlesDao: ArticlesDao,
    val context: Context
) {

    suspend fun insertArticle(dogArticle: DogArticle) {


        try {
            val entity = ArticlesEntity.fromFeed(dogArticle)
            runBlocking {
                articlesDao.insertArticle(entity)
            }

        } catch (e: SQLiteConstraintException) {

        }
    }

    suspend fun loadArticles(): List<DogArticle> {
        val entitiesList = articlesDao.loadFavouritesArticles()
        return entitiesList.map {
            DogArticle(it.url, mutableListOf(it.fact))
        }
    }

    suspend fun deleteArticle(fact: String) {
        articlesDao.deleteArticle(fact)
    }
}


