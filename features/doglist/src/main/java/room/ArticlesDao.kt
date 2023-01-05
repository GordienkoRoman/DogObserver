package room

import androidx.room.Dao
import androidx.room.Insert
import room.entities.ArticlesEntity

@Dao
interface ArticlesDao {
    @Insert
    fun InsertArticle(articlesEntity: ArticlesEntity)

}