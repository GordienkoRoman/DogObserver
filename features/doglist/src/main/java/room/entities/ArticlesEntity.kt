package room.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.utils.models.DogArticle

@Entity(
    tableName = "articles",
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
        fun fromFeed(dogArticle: DogArticle): ArticlesEntity = ArticlesEntity(
            id = 0,
            url = dogArticle.url,
            fact = dogArticle.facts[0]
        )
    }

}