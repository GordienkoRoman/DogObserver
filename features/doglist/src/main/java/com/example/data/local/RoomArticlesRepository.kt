package com.example.data.local

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.example.utils.models.DogArticle
import kotlinx.coroutines.runBlocking
import com.example.data.local.entities.ArticlesEntity
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

        } catch (_: SQLiteConstraintException) {

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


