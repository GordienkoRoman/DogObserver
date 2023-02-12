package di

import Repository.DogArticlesRepository
import androidx.lifecycle.ViewModel
import com.example.doglist.DogListFragment
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.Provides
import implementations.DogArticlesRepositoryImpl
import restAPI.DogApiFactsService
import restAPI.DogApiImgService
import javax.inject.Scope
import javax.inject.Singleton
import kotlin.reflect.KClass

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Feature

@[Feature Component(modules = [ArticleModule::class],dependencies = [ArticlesDeps::class])]
internal interface ArticlesComponent {
    fun inject(fragment: DogListFragment)

    @Component.Builder
    interface Builder
    {
     //   fun dependencies(dependencies: ArticlesDependencies): Builder
        fun deps(deps: ArticlesDeps):Builder

        fun Build(): ArticlesComponent
    }

}
@Module
class ArticleModule{

    @Feature
    @Provides
    fun provideDogArticleRepo(dogApiFactsService: DogApiFactsService,dogApiImgService: DogApiImgService)
    : DogArticlesRepository {
        return DogArticlesRepositoryImpl(dogApiFactsService,dogApiImgService)
    }

//    @Singleton
//    @Provides
//    fun provideDogsViewModel(dogArticlesRepository: DogArticlesRepository):DogsViewModel
//    {
//        return DogsViewModel(dogArticlesRepository)
//
//    }
}



