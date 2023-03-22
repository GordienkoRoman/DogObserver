package room

import androidx.room.Database
import androidx.room.RoomDatabase
import room.entities.ArticlesEntity

@Database(
    version = 1,
    entities = [ArticlesEntity::class]
)
abstract class  AppDatabase:RoomDatabase() {
    abstract fun ArticlesDao(): ArticlesDao
}