package implementations

import Repository.DogArticlesRepository
import com.example.utils.models.DogArticle
import restAPI.DogApiFactsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DogArticlesRepositoryImpl @Inject constructor(
   val dogApiFactsService: DogApiFactsService
   // private val apiFactsService: ArticleService.DogApiFactsService
) : DogArticlesRepository {

    private val urlImg = "https://i.pinimg.com/564x/15/36/e7/1536e7de67f8f992c595a308ec8ae363.jpg"

    override fun getDogArticle() : DogArticle {
        lateinit var article: DogArticle
        val t = object : Callback<DogArticle> {
            override fun onResponse(call: Call<DogArticle>, response: Response<DogArticle>) {
                updateItem( response.body() as DogArticle)
                article = response.body() as DogArticle
            }

            override fun onFailure(call: Call<DogArticle>, t: Throwable) {
                updateItem( DogArticle(urlImg,mutableListOf()))
            }
        }
        //mServiceImg.getDogImg().enqueue(t)
        return dogApiFactsService
            .getDogFact()
            .execute()
            .body() as DogArticle
          /*  dogApiFactsService.getDogFact().enqueue(t)
        return DogArticle("", mutableListOf())*/
    }

    private fun updateItem( dogArticle: DogArticle) {
        /*if(dogArticle.facts!=null&&dogArticle.facts.size==1)
            _dogArticleList[position].facts = dogArticle.facts
        else
            _dogArticleList[position].url = dogArticle.url
             if(dogArticle.facts[0]==null)
          if(_dogArticleList[position].facts.size!=0&&_dogArticleList[position].url!="")*/

    }
}