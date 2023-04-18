package com.example.di.components

import android.content.Context
import com.example.ui.doglist.DogListFragment
import dagger.*
import com.example.di.ArticlesDeps
import com.example.di.modules.ArticleModule
import com.example.di.modules.DataBaseModule
import com.example.ui.favourites.FavouritesFragment
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Feature

@[Feature Component(modules = [ArticleModule::class, DataBaseModule::class],dependencies = [ArticlesDeps::class])]
internal interface ArticlesComponent {
    fun inject(fragment: DogListFragment)
    fun inject(fragment: FavouritesFragment)

    @Component.Builder
    interface Builder
    {
     //   fun dependencies(dependencies: ArticlesDependencies): Builder
        fun deps(deps: ArticlesDeps): Builder

       @BindsInstance
        fun application(viewModel: ArticlesComponentViewModel): Builder

        @BindsInstance
        fun context(context: Context): Builder


        fun Build(): ArticlesComponent
    }

}





