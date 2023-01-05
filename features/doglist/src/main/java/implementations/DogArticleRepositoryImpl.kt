package implementations

import Repository.DogArticlesRepository
import com.example.utils.DogApiImgService
import javax.inject.Inject

class DogArticlesRepositoryImpl @Inject constructor(
    private val apiImgService: DogApiImgService,
   // private val apiFactsService: ArticleService.DogApiFactsService
) : DogArticlesRepository {
    override fun loadItem() {
        apiImgService.getDogImg()
        TODO("Not yet implemented")
    }
}