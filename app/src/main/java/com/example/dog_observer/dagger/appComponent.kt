package com.example.dog_observer.dagger

import Repository.DogArticlesRepository
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.example.dog_observer.BuildConfig
import com.example.dog_observer.MainActivityVeiwModel
import com.example.doglist.DogsViewModel
import dagger.*
import dagger.multibindings.IntoMap
import di.ArticlesDeps
import implementations.DogArticlesRepositoryImpl
import restAPI.DogApiFactsService
import restAPI.DogApiImgService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Provider
import javax.inject.Qualifier
import javax.inject.Scope
import javax.inject.Singleton
import kotlin.reflect.KClass

@Scope
annotation class AppScope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : ArticlesDeps {

    override val factsService: DogApiFactsService
    override val imgService: DogApiImgService
    //fun inject(application: Application)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}


@Module
class AppModule {
    /*  @Provides
      fun providesArticleService(): DogApiImgService = DogApiImgService()*/


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @[Provides AppScope]
    fun provideDogApiFactService(): DogApiFactsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog-api.kinduff.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(DogApiFactsService::class.java)
    }


    @Provides
    fun provideDogApiImgService(): DogApiImgService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://random.dog/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(DogApiImgService::class.java)
    }


    /*@Provides
    @Singleton
    fun provideRetrofitBuilder(
        converterFactory: GsonConverterFactory,
        context: Context
    ): Retrofit {
       return Retrofit.Builder()
            .baseUrl("https://dog-api.kinduff.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/

}

