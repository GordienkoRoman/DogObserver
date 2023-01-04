package restAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCLient(url:String) {
/*    private var retrofit: Retrofit? = null
    private val BASE_URL = url
    val retrofitFactService: DogApiFactsService
        get() = getClient(BASE_URL).create(DogApiFactsService::class.java)
    val retrofitImageService: DogApiImgService
        get() = getClient(BASE_URL).create(DogApiImgService::class.java)

    private fun getClient(baseUrl: String): Retrofit {
       // if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
      //  }
        return retrofit!!
    }*/
}