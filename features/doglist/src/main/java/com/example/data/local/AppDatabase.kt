package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.entities.ArticlesEntity

@Database(
    version = 1,
    entities = [ArticlesEntity::class]
)
abstract class  AppDatabase:RoomDatabase() {
    abstract fun ArticlesDao(): ArticlesDao
}