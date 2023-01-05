package room

import android.database.sqlite.SQLiteConstraintException
import com.example.utils.models.DogArticle
import room.entities.ArticlesEntity

class RoomArticlesRepository(
    private val articlesDao: ArticlesDao
) {
     fun createArticle(dogArticle: DogArticle)
    {
        try{
            val entity = ArticlesEntity.fromFeed(dogArticle)
            articlesDao.InsertArticle(entity)
        }
        catch (e: SQLiteConstraintException){

        }
    }
}