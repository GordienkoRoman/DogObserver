package room.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.utils.models.DogArticle
import room.entities.ArticlesEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [
        Index("url", "fact", unique = true)
    ]
)
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val url: String,
    val fact: String,
) {
    fun toArticle(): DogArticle = DogArticle(
        url = url,
        facts = mutableListOf(fact),
        isFavourite = true
    )
    companion object {
        const val TABLE_NAME = "articles"
        fun fromFeed(dogArticle: DogArticle): ArticlesEntity = ArticlesEntity(
            id = 0,
            url = dogArticle.url,
            fact = dogArticle.facts[0]
        )
    }

}