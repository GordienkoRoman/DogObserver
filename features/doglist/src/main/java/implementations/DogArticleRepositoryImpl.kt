package implementations

import Repository.DogArticlesRepository
import com.example.utils.models.DogArticle
import restAPI.DogApiFactsService
import restAPI.DogApiImgService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DogArticlesRepositoryImpl @Inject constructor(
   private val dogApiFactsService: DogApiFactsService,
   private val dogApiImgService : DogApiImgService
) : DogArticlesRepository {

    private val urlImg = "https://i.pinimg.com/564x/15/36/e7/1536e7de67f8f992c595a308ec8ae363.jpg"

    override fun getDogFact() = dogApiFactsService.getDogFact()
    override fun getDogImg() = dogApiImgService.getDogImg()


}