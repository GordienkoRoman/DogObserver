package room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import room.entities.ArticlesEntity

@Dao
interface ArticlesDao {
    @Insert(entity = ArticlesEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(articlesEntity: ArticlesEntity)

    @Query("SELECT * FROM ${ArticlesEntity.TABLE_NAME}")
   suspend fun loadFavouritesArticles(): List<ArticlesEntity>
}