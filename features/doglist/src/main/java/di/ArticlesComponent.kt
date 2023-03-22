package di

import Repository.DogArticlesRepository
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.doglist.DogListFragment
import dagger.*
import di.modules.ArticleModule
import di.modules.DataBaseModule
import favourites.FavouritesFragment
import implementations.DogArticlesRepositoryImpl
import restAPI.DogApiFactsService
import restAPI.DogApiImgService
import javax.inject.Scope
import javax.inject.Singleton
import kotlin.reflect.KClass

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Feature

@[Feature Component(modules = [ArticleModule::class,DataBaseModule::class],dependencies = [ArticlesDeps::class])]
internal interface ArticlesComponent {
    fun inject(fragment: DogListFragment)
    fun inject(fragment: FavouritesFragment)

    @Component.Builder
    interface Builder
    {
     //   fun dependencies(dependencies: ArticlesDependencies): Builder
        fun deps(deps: ArticlesDeps):Builder

       @BindsInstance
        fun application(viewModel: ArticlesComponentViewModel): Builder

        @BindsInstance
        fun context(context: Context):Builder


        fun Build(): ArticlesComponent
    }

}





