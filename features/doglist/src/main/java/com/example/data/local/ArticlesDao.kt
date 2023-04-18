package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.ArticlesEntity

@Dao
interface ArticlesDao {
    @Insert(entity = ArticlesEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(articlesEntity: ArticlesEntity)

    @Query("SELECT * FROM ${ArticlesEntity.TABLE_NAME}")
   suspend fun loadFavouritesArticles(): List<ArticlesEntity>
   @Query("DELETE FROM ${ArticlesEntity.TABLE_NAME} WHERE :fact = fact")
   suspend fun deleteArticle(fact : String)
}