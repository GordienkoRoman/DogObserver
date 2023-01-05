package com.example.dog_observer.dagger

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.example.dog_observer.BuildConfig
import com.example.dog_observer.MainActivityVeiwModel
import com.example.utils.DogApiImgService
import dagger.BindsInstance
import di.ArticlesDependencies
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Component(modules = [AppModule::class])
interface AppComponent : ArticlesDependencies {
    override val dogApiImgService: DogApiImgService

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}


@Module
class AppModule{
    @Provides
    fun providesArticleService(): DogApiImgService = DogApiImgService()


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

   /* @Provides
    fun provideDogApiFactService(
        retrofit: Retrofit): DogApiFactsService {
        return retrofit.create(DogApiFactsService::class.java)
    }*/

 /*   @Provides
    fun provideDogApiImgService(

        retrofit: Retrofit): DogApiImgService {
        return retrofit.create(DogApiImgService::class.java)
    }*/

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        converterFactory: GsonConverterFactory,
        context: Context
    ): Retrofit {
       return Retrofit.Builder()
            .baseUrl("https://dog-api.kinduff.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

