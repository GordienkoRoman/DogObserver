package restAPI

import com.example.utils.models.DogArticle
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface DogApiImgService {

    /*@GET("woof.json")
    fun getDogImg():Call<DogArticle>*/

    @GET("woof.json")
    suspend fun getDogImg(): Response<DogArticle>
}
interface DogApiFactsService {
   /* @GET("api/facts")
    fun getDogFact(): Call<DogArticle>*/

    @GET("api/facts")
    suspend fun getDogFact(): Response<DogArticle>
}
