package di

import com.example.doglist.DogListFragment
import com.example.doglist.DogsViewModel
import com.example.utils.DogApiImgService
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Feature

@[Feature Component(dependencies = [ArticlesDependencies::class])]
internal interface ArticlesComponent {
    fun inject(fragment: DogListFragment)
    @Component.Builder
    interface Builder
    {
        fun dependencies(dependencies: ArticlesDependencies): Builder

        fun Build(): ArticlesComponent
    }
}

interface ArticlesDependencies
{
    //val repository: DogArticlesRepository
    val dogApiImgService: DogApiImgService
}


